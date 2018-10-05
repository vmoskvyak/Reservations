package com.vmoskvyak.reservations

import android.content.BroadcastReceiver
import com.vmoskvyak.reservations.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasBroadcastReceiverInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class ReservationsApplication : DaggerApplication(), HasActivityInjector,
        HasBroadcastReceiverInjector {

    @Inject
    lateinit var broadcastReceiverInjector: DispatchingAndroidInjector<BroadcastReceiver>

    override fun applicationInjector(): AndroidInjector<DaggerApplication> {
        val appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)

        return appComponent
    }

    override fun broadcastReceiverInjector(): DispatchingAndroidInjector<BroadcastReceiver> {
        return broadcastReceiverInjector
    }

}
