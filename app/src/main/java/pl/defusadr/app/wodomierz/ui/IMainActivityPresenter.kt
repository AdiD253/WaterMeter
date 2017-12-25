package pl.defusadr.app.wodomierz.ui

import pl.defusadr.app.wodomierz.di.ActivityScope
import pl.defusadr.app.wodomierz.model.WaterMeterValue

@ActivityScope
interface IMainActivityPresenter<V : IMainActivityView> {

    fun attachView(view: V)
    fun detachView()
    fun loadData()
    fun trySaveValue(integerInput: String, decimalInput: String)
    fun editValue(waterMeterValue: WaterMeterValue)
    fun removeValue(waterMeterValue: WaterMeterValue)
}