package pl.defusadr.app.wodomierz.ui

import pl.defusadr.app.wodomierz.model.WaterMeterValue

interface IMainActivityView {

    fun populateList(values: MutableList<WaterMeterValue>)
    fun addValue(value: WaterMeterValue)
    fun notifyItemRemoved()
    fun showMessage(message: String)
    fun showEmptyView()
}