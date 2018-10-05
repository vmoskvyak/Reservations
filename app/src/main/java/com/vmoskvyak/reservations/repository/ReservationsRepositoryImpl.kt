package com.vmoskvyak.reservations.repository

import android.arch.lifecycle.LiveData
import com.vmoskvyak.reservations.db.dao.ReservationsDAO
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.ReservationsApi
import com.vmoskvyak.reservations.network.model.CustomerModel
import retrofit2.Response
import javax.inject.Inject

class ReservationsRepositoryImpl
    @Inject constructor(private val reservationsApi: ReservationsApi,
                        private val reservationsDAO: ReservationsDAO): ReservationsRepository {

    override suspend fun getCustomers(): Response<List<CustomerModel>> {
        return reservationsApi.getCustomerList().await()
    }

    override suspend fun getTables(): Response<List<Boolean>> {
        return reservationsApi.getTables().await()
    }

    override fun saveCustomersToLocal(customers: List<CustomerDTO>) {
        reservationsDAO.insertCustomers(customers)
    }

    override fun getCustomersFromLocal(): LiveData<List<CustomerDTO>> {
        return reservationsDAO.getCustomers()
    }

    override fun saveTablesToLocal(tables: List<TableDTO>) {
        reservationsDAO.insertTableReservations(tables)
    }

    override fun getTablesFromLocal(): LiveData<List<TableDTO>> {
        return reservationsDAO.getTableReservations()
    }

    override fun updateTableReservation(id: Long?, reserved: Boolean) {
        reservationsDAO.updateTableReservation(id, reserved)
    }

    override fun hasCustomersLocal(): Boolean {
        return reservationsDAO.hasCustomers().isNotEmpty()
    }

    override fun hasTablesLocal(): Boolean {
        return reservationsDAO.hasTableReservations().isNotEmpty()
    }

    override fun clearCustomers() {
        reservationsDAO.deleteCustomers()
    }

    override fun clearReservations() {
        reservationsDAO.deleteTableReservations()
    }

}
