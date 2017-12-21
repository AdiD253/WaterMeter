package pl.defusadr.app.wodomierz.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "waterMeterValues")
data class WaterMeterValue (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,

        @ColumnInfo(name = "amount")
        var amount: Float,

        @ColumnInfo(name = "date")
        var date: Long,

        @ColumnInfo(name = "description")
        var description: String = ""
)