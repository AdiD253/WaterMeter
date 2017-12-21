package pl.defusadr.app.wodomierz.di.module

import android.app.Application
import android.content.Context
import dagger.Provides
import pl.defusadr.app.wodomierz.db.AppDatabase
import javax.inject.Singleton

class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideDatabase(context: Context) : AppDatabase = AppDatabase.getDatabase(context)

}