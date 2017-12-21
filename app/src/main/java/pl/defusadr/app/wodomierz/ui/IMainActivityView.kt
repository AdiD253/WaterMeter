package pl.defusadr.app.wodomierz.ui

import pl.defusadr.app.wodomierz.model.WaterMeterValue

interface IMainActivityView {

    fun addValue(value: WaterMeterValue)
}