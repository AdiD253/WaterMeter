package pl.defusadr.app.wodomierz.ui

import io.reactivex.Single
import pl.defusadr.app.wodomierz.db.AppDatabase
import pl.defusadr.app.wodomierz.model.WaterMeterValue
import javax.inject.Inject

class MainDataManager @Inject constructor(private val database: AppDatabase) : IMainDataManager {

    override fun loadAllValues(): Single<MutableList<WaterMeterValue>> =
            Single.just<MutableList<WaterMeterValue>>(database.waterMeterDao().getAllValues())

    override fun loadValueById(id: Long): Single<WaterMeterValue> =
            Single.just<WaterMeterValue>(database.waterMeterDao().getValueById(id))

    override fun insertValue(value: WaterMeterValue) = database.waterMeterDao().insertValue(value)

    override fun updateValue(value: WaterMeterValue) = database.waterMeterDao().updateValue(value)

    override fun deleteValue(value: WaterMeterValue) = database.waterMeterDao().deleteValue(value)
}