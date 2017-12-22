package pl.defusadr.app.wodomierz.di.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import pl.defusadr.app.wodomierz.db.AppDatabase
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : AppDatabase =
            Room.databaseBuilder(application, AppDatabase::class.java, "water_meter_db").allowMainThreadQueries().build()

}