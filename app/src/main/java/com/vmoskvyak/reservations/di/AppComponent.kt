package com.vmoskvyak.reservations.di

import android.app.Application
import com.vmoskvyak.githubreposearch.di.builder.ActivityBuilder
import com.vmoskvyak.githubreposearch.di.builder.FragmentBuilder
import com.vmoskvyak.reservations.ReservationsApplication
import com.vmoskvyak.reservations.di.builder.BroadcastReceiverBuilder
import com.vmoskvyak.reservations.di.mudules.DataModule
import com.vmoskvyak.reservations.di.mudules.NetworkModule
import com.vmoskvyak.reservations.di.mudules.RoomModule
import com.vmoskvyak.reservations.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@AppScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    NetworkModule::class,
    DataModule::class,
    RoomModule::class,
    BroadcastReceiverBuilder::class,
    ActivityBuilder::class,
    FragmentBuilder::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: ReservationsApplication)

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}