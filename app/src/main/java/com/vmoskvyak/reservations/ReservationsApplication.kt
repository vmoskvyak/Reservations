package com.vmoskvyak.reservations

import com.vmoskvyak.reservations.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

class ReservationsApplication : DaggerApplication(), HasActivityInjector {

    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)

        return appComponent
    }

}
