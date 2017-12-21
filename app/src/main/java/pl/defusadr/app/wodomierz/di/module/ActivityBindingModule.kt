package pl.defusadr.app.wodomierz.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.defusadr.app.wodomierz.di.ActivityScope
import pl.defusadr.app.wodomierz.ui.MainActivity

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    @ActivityScope
    abstract fun bindMainActivity(): MainActivity
}

