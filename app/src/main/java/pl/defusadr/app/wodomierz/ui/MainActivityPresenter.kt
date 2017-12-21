package pl.defusadr.app.wodomierz.ui

import android.text.TextUtils
import pl.defusadr.app.wodomierz.db.AppDatabase
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import java.text.ParseException
import javax.inject.Inject

class MainActivityPresenter<V : IMainActivityView> @Inject constructor(): IMainActivityPresenter<V> {

    @Inject
    lateinit var database: AppDatabase

    private var view : V? = null

    override fun attachView(view: V) {
       this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadData() {
        val valuesList = database.waterMeterDao().getAllValues()
        view?.populateList(valuesList)
    }

    override fun trySaveValue(integerInput: String, decimalInput: String) {
        if (TextUtils.isEmpty(integerInput)) {
            view?.showError("Wprowadzana wartość nie może być pusta!")
        } else {
            try {
                val integerValue = Integer.valueOf(integerInput)
                var decimalValue = 0F

                when (decimalInput.length) {
                    3 -> decimalValue = decimalInput.toFloat()
                    2 -> decimalValue = (decimalInput + "0").toFloat()
                    1 -> decimalValue = (decimalInput + "00").toFloat()
                    0 -> decimalValue = 0F
                }
                val value = integerValue + decimalValue/1000

                val waterMeterValue = WaterMeterValue(amount = value, date = System.currentTimeMillis())
                database.waterMeterDao().insertValue(waterMeterValue)
                view?.addValue(waterMeterValue)

            } catch (e: ParseException) {
                view?.showError("Wprowadzona wartość w polu nie jest liczbą całkowitą")
            }
        }


    }

}
