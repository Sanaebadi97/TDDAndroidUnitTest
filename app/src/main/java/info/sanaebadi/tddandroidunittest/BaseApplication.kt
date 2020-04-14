package info.sanaebadi.tddandroidunittest

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import info.sanaebadi.tddandroidunittest.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build();
    }

}