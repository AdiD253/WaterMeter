package pl.defusadr.app.wodomierz.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import pl.defusadr.app.wodomierz.App
import pl.defusadr.app.wodomierz.di.module.ActivityBindingModule
import pl.defusadr.app.wodomierz.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
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
