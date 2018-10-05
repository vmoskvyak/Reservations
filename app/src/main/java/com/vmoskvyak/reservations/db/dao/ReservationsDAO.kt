package com.vmoskvyak.reservations.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.db.entity.TableDTO

@Dao
interface ReservationsDAO {

    @Query("select * from customer")
    fun getCustomers() : LiveData<List<CustomerDTO>>

    @Query("select * from customer")
    fun hasCustomers() : List<CustomerDTO>

    @Insert(onConflict = REPLACE)
    fun insertCustomers(customersDTO: List<CustomerDTO>)

    @Query("delete from customer")
    fun deleteCustomers()

    @Query("select * from table_reservations")
    fun getTableReservations() : LiveData<List<TableDTO>>

    @Query("select * from table_reservations")
    fun hasTableReservations() : List<TableDTO>

    @Insert(onConflict = REPLACE)
    fun insertTableReservations(tableDTO: List<TableDTO>)

    @Query("update table_reservations set is_reserved = :reserved where id == :id")
    fun updateTableReservation(id: Long?, reserved: Boolean)

    @Query("delete from table_reservations")
    fun deleteTableReservations()

}
