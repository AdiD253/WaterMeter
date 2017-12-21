package pl.defusadr.app.wodomierz.di.module

import dagger.Binds
import dagger.Module
import pl.defusadr.app.wodomierz.ui.IMainActivityPresenter
import pl.defusadr.app.wodomierz.ui.IMainActivityView
import pl.defusadr.app.wodomierz.ui.MainActivityPresenter

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivityPresenter(presenter: MainActivityPresenter<IMainActivityView>)
            : IMainActivityPresenter<IMainActivityView>
}