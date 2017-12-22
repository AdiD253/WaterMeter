package pl.defusadr.app.wodomierz.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "waterMeterValues")
class WaterMeterValue (

        @ColumnInfo(name = "amount")
        var amount: Float = 0.0F,

        @ColumnInfo(name = "date")
        var date: Long = 0,

        @ColumnInfo(name = "description")
        var description: String = ""
) {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
}