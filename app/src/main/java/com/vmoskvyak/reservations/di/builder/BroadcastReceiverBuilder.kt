package com.vmoskvyak.reservations.di.builder

import com.vmoskvyak.reservations.broadcast.ReservationsBroadcastReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BroadcastReceiverBuilder {

    @ContributesAndroidInjector
    abstract fun contribureReservationsBroadcastReceiver() : ReservationsBroadcastReceiver

}
