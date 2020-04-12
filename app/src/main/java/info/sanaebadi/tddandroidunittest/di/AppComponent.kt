package info.sanaebadi.tddandroidunittest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import info.sanaebadi.tddandroidunittest.util.BaseApplication


@Component(modules = {
    AndroidInjectionModule::class,

})

interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}