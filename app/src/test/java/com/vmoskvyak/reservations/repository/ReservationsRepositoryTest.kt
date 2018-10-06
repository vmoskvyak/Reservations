package com.vmoskvyak.reservations.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.vmoskvyak.reservations.db.dao.ReservationsDAO
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.ReservationsApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class ReservationsRepositoryTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var reservationsRepository: ReservationsRepository

    private val reservationsApi = mock(ReservationsApi::class.java)
    private val reservationsDAO = mock(ReservationsDAO::class.java)

    @Before
    fun setUp() {
        reservationsRepository = ReservationsRepositoryImpl(reservationsApi, reservationsDAO)
    }


    @Test
    fun saveCustomersToLocal() {
        val customers = ArrayList<CustomerDTO>()
        customers.add(CustomerDTO(1, "first name", "last name"))
        reservationsRepository.saveCustomersToLocal(customers)

        Mockito.verify(reservationsDAO).insertCustomers(customers)
    }

    @Test
    fun getCustomersFromLocal() {
        reservationsRepository.getCustomersFromLocal()
        Mockito.verify(reservationsDAO).getCustomers()
    }

    @Test
    fun saveTablesToLocal() {
        val tables = ArrayList<TableDTO>()
        val tableDTO = TableDTO()
        tableDTO.isReserved = true
        tables.add(tableDTO)

        reservationsRepository.saveTablesToLocal(tables)
        Mockito.verify(reservationsDAO).insertTableReservations(tables)
    }

    @Test
    fun getTablesFromLocal() {
        reservationsRepository.getTablesFromLocal()
        Mockito.verify(reservationsDAO).getTableReservations()
    }

    @Test
    fun updateTableReservation() {
        reservationsRepository.updateTableReservation(1, true)
        Mockito.verify(reservationsDAO).updateTableReservation(1, true)
    }

    @Test
    fun hasCustomersLocal() {
        reservationsRepository.hasCustomersLocal()
        Mockito.verify(reservationsDAO).hasCustomers()
    }

    @Test
    fun hasTablesLocal() {
        reservationsRepository.hasTablesLocal()
        Mockito.verify(reservationsDAO).hasTableReservations()
    }

    @Test
    fun clearCustomers() {
        reservationsRepository.clearCustomers()
        Mockito.verify(reservationsDAO).deleteCustomers()
    }

    @Test
    fun clearReservations() {
        reservationsRepository.clearReservations()
        Mockito.verify(reservationsDAO).deleteTableReservations()
    }

}
