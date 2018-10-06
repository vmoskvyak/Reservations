package com.vmoskvyak.reservations.db.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.vmoskvyak.reservations.LiveDataTestUtil
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.db.entity.TableDTO
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ReservationsDAOTest {

    private lateinit var reservationsDatabase: ReservationsDatabase

    @Before
    fun initDB() {
        reservationsDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ReservationsDatabase::class.java).build()
    }

    @After
    fun closeDB() {
        reservationsDatabase.close()
    }

    @Test
    fun insertAndGetCustomers() {
        val customers = ArrayList<CustomerDTO>()
        customers.add(CustomerDTO(1, "first name", "last name"))

        reservationsDatabase.reservationsDAO().insertCustomers(customers)
        val list = LiveDataTestUtil.getValue(reservationsDatabase.reservationsDAO().getCustomers())

        Assert.assertEquals(customers, list)
    }

    @Test
    fun insertAndGetTableReservations() {
        val tables = ArrayList<TableDTO>()
        val tableDTO = TableDTO()
        tableDTO.id = 1
        tableDTO.isReserved = true
        tables.add(tableDTO)

        reservationsDatabase.reservationsDAO().insertTableReservations(tables)
        val list = LiveDataTestUtil.getValue(reservationsDatabase.reservationsDAO()
                .getTableReservations())

        Assert.assertEquals(tables, list)
    }

    @Test
    fun hastCustomers() {
        val hasCustomers = reservationsDatabase.reservationsDAO().hasCustomers()

        assert(hasCustomers.isEmpty())
    }

    @Test
    fun hasTableReservations() {
        val hasTableReservations = reservationsDatabase.reservationsDAO().hasTableReservations()

        assert(hasTableReservations.isEmpty())
    }

    @Test
    fun deleteCustomers() {
        val customers = ArrayList<CustomerDTO>()
        customers.add(CustomerDTO(1, "first name", "last name"))

        reservationsDatabase.reservationsDAO().insertCustomers(customers)
        var list = LiveDataTestUtil.getValue(reservationsDatabase.reservationsDAO().getCustomers())

        Assert.assertEquals(customers, list)

        reservationsDatabase.reservationsDAO().deleteCustomers()
        list = LiveDataTestUtil.getValue(reservationsDatabase.reservationsDAO().getCustomers())
        assert(list.isEmpty())
    }

    @Test
    fun deleteTableReservations() {
        val tables = ArrayList<TableDTO>()
        val tableDTO = TableDTO()
        tableDTO.id = 1
        tableDTO.isReserved = true
        tables.add(tableDTO)

        reservationsDatabase.reservationsDAO().insertTableReservations(tables)
        var list = LiveDataTestUtil.getValue(reservationsDatabase.reservationsDAO()
                .getTableReservations())

        Assert.assertEquals(tables, list)

        reservationsDatabase.reservationsDAO().deleteTableReservations()
        list = LiveDataTestUtil.getValue(reservationsDatabase.reservationsDAO()
                .getTableReservations())
        assert(list.isEmpty())
    }

}
