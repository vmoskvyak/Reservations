package com.vmoskvyak.reservations.db.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.db.entity.TableDTO

@Database(entities = [CustomerDTO::class, TableDTO::class], version = 1)
abstract class ReservationsDatabase : RoomDatabase() {

    abstract fun reservationsDAO() : ReservationsDAO

}
