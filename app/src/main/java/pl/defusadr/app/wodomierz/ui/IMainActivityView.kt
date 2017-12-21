package pl.defusadr.app.wodomierz.ui

import pl.defusadr.app.wodomierz.model.WaterMeterValue

interface IMainActivityView {

    fun loadData()
    fun populateList(values: MutableList<WaterMeterValue>)
    fun addValue(value: WaterMeterValue)
    fun showError(message: String)
}