package pl.defusadr.app.wodomierz.di.module

import dagger.Binds
import dagger.Module
import pl.defusadr.app.wodomierz.di.ActivityScope
import pl.defusadr.app.wodomierz.ui.*

@Module
abstract class MainActivityModule {

    @Binds
    @ActivityScope
    abstract fun provideMainActivityPresenter(presenter: MainActivityPresenter<IMainActivityView>)
            : IMainActivityPresenter<IMainActivityView>

    @Binds
    @ActivityScope
    abstract fun provideMainDataManager(datamanager: MainDataManager) : IMainDataManager
}