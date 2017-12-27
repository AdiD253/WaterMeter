package pl.defusadr.app.wodomierz.ui

import pl.defusadr.app.wodomierz.di.ActivityScope
import pl.defusadr.app.wodomierz.model.WaterMeterValue

@ActivityScope
interface IMainActivityPresenter<V : IMainActivityView> {

    fun attachView(view: V)
    fun detachView()
    fun loadData()
    fun saveValue(integerInput: String, decimalInput: String)
    fun deleteValue(waterMeterValue: WaterMeterValue)
}