package info.sanaebadi.tddandroidunittest.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import info.sanaebadi.tddandroidunittest.viewModel.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory):
            ViewModelProvider.Factory

}