package pl.defusadr.app.wodomierz.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import pl.defusadr.app.wodomierz.App
import pl.defusadr.app.wodomierz.di.module.ActivityBindingModule
import pl.defusadr.app.wodomierz.di.module.AppModule
import pl.defusadr.app.wodomierz.di.module.DatabaseModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DatabaseModule::class,
        ActivityBindingModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
