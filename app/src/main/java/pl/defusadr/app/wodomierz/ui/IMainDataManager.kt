package pl.defusadr.app.wodomierz.ui

import io.reactivex.Single
import pl.defusadr.app.wodomierz.model.WaterMeterValue

interface IMainDataManager {

    fun loadAllValues(): Single<MutableList<WaterMeterValue>>
    fun loadValueById(id: Long): Single<WaterMeterValue>
    fun insertValue(value: WaterMeterValue)
    fun updateValue(value: WaterMeterValue)
    fun deleteValue(value: WaterMeterValue)
}