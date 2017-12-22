package pl.defusadr.app.wodomierz.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.defusadr.app.wodomierz.db.AppDatabase
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

}