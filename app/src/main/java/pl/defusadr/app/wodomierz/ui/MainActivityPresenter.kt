package pl.defusadr.app.wodomierz.ui

import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import javax.inject.Inject

class MainActivityPresenter<V : IMainActivityView> @Inject constructor(private val dataManager: IMainDataManager) : IMainActivityPresenter<V> {

    private var view: V? = null
    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        disposable.clear()
        this.view = null
    }

    override fun loadData() {
        disposable +=
                dataManager.loadAllValues()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onError = {
                                    view?.showError("Database error")
                                },
                                onSuccess = {
                                    if (it.isEmpty()) {
                                        view?.showEmptyView()
                                    } else {
                                        view?.populateList(it)
                                    }
                                }
                        )
    }

    override fun trySaveValue(integerInput: String, decimalInput: String) {
        if (TextUtils.isEmpty(integerInput) && TextUtils.isEmpty(decimalInput)) {
            view?.showError("Wprowadzana wartość nie może być pusta!")
        } else {
            try {
                val integerValue = if (integerInput.isEmpty()) {
                    0
                } else {
                    Integer.valueOf(integerInput)
                }
                var decimalValue = 0F

                when (decimalInput.length) {
                    3 -> decimalValue = decimalInput.toFloat()
                    2 -> decimalValue = (decimalInput + "0").toFloat()
                    1 -> decimalValue = (decimalInput + "00").toFloat()
                    0 -> decimalValue = 0F
                }
                val value = integerValue + decimalValue / 1000

                val waterMeterValue = WaterMeterValue(amount = value, date = System.currentTimeMillis())
                dataManager.insertValue(waterMeterValue)
                view?.addValue(waterMeterValue)

            } catch (e: NumberFormatException) {
                view?.showError("Niepoprawny format liczby")
            }
        }
    }

    override fun editValue(waterMeterValue: WaterMeterValue) {
        TODO("not implemented")
    }

    override fun removeValue(waterMeterValue: WaterMeterValue) {
        TODO("not implemented")
    }
}
