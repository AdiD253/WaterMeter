package pl.defusadr.app.wodomierz.ui

import pl.defusadr.app.wodomierz.db.AppDatabase
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import javax.inject.Inject

class MainActivityPresenter<V : IMainActivityView> : IMainActivityPresenter<V> {

    @Inject
    lateinit var database: AppDatabase

    private var view : V? = null

    override fun onAttachView(view: V) {
       this.view = view
    }

    override fun onDetachView() {
        this.view = null
    }

    override fun loadData() {
        val valuesList = database.waterMeterDao().getAllValues()

    }

    override fun saveValue(value: Float) {
        val waterMeterValue = WaterMeterValue(amount = value, date = System.currentTimeMillis())
        database.waterMeterDao().insertValue(waterMeterValue)
        view?.addValue(waterMeterValue)
    }


}
