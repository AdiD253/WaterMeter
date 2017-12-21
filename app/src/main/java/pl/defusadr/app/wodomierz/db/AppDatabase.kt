package pl.defusadr.app.wodomierz.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import pl.defusadr.app.wodomierz.dao.WaterMeterDao
import pl.defusadr.app.wodomierz.model.WaterMeterValue

@Database(entities = arrayOf(WaterMeterValue::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun waterMeterDao(): WaterMeterDao

    companion object {
        private val dbName = "water_meter_db"
        var dbInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder<AppDatabase>(context, AppDatabase::class.java, dbName).build()
            }
            return dbInstance as AppDatabase
        }
    }

}