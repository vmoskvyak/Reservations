package com.vmoskvyak.reservations.repository

import android.arch.lifecycle.LiveData
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.model.CustomerModel
import retrofit2.Response

interface ReservationsRepository {

    suspend fun getCustomers() : Response<List<CustomerModel>>

    suspend fun getTables() : Response<List<Boolean>>

    fun saveCustomersToLocal(customers: List<CustomerDTO>)

    fun getCustomersFromLocal() : LiveData<List<CustomerDTO>>

    fun saveTablesToLocal(tables: List<TableDTO>)

    fun getTablesFromLocal() : LiveData<List<TableDTO>>

    fun updateTableReservation(id: Long?, reserved: Boolean)

    fun hasCustomersLocal() : Boolean

    fun hasTablesLocal() : Boolean

    fun clearCustomers()

    fun clearReservations()

}
