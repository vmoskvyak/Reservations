package com.vmoskvyak.reservations.di.mudules

import android.app.Application
import android.arch.persistence.room.Room
import com.vmoskvyak.reservations.db.dao.ReservationsDAO
import com.vmoskvyak.reservations.db.dao.ReservationsDatabase
import com.vmoskvyak.reservations.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class RoomModule {

    @AppScope
    @Provides
    fun provideReservationsDatabase(mApplication: Application): ReservationsDatabase {
        return Room.databaseBuilder(mApplication, ReservationsDatabase::class.java,
                "reservations-db").build()
    }

    @AppScope
    @Provides
    fun provideReservationsDao(reservationsDatabase: ReservationsDatabase): ReservationsDAO {
        return reservationsDatabase.reservationsDAO()
    }

}
