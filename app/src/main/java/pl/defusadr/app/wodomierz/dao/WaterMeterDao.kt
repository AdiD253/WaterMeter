package pl.defusadr.app.wodomierz.dao

import android.arch.persistence.room.*
import pl.defusadr.app.wodomierz.model.WaterMeterValue

@Dao
interface WaterMeterDao {

    @Query("SELECT * FROM waterMeterValues")
    fun getAllValues(): MutableList<WaterMeterValue>

    @Query("SELECT * FROM waterMeterValues WHERE id = :arg0")
    fun getValueById(id: Long): WaterMeterValue

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertValue(value: WaterMeterValue)

    @Update
    fun updateValue(value: WaterMeterValue)

    @Delete
    fun deleteValue(value: WaterMeterValue)
}