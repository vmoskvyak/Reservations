package com.vmoskvyak.reservations.network.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TableModelTest {

    private lateinit var tableModel: TableModel

    @Before
    fun setup() {
        tableModel = TableModel()
        tableModel.id = 1
        tableModel.reserved = true
    }

    @Test
    fun getId() {
        assertEquals(1L, tableModel.id)
    }

    @Test
    fun setId() {
        tableModel.id = 2
        assertEquals(2L, tableModel.id!!)
    }

    @Test
    fun getReserved() {
        assertEquals(true, tableModel.reserved)
    }

    @Test
    fun setReserved() {
        tableModel.reserved = false
        assertEquals(false, tableModel.reserved)
    }

    @Test
    fun tableModelObject() {
        val newTableModel = TableModel()
        newTableModel.id = 1
        newTableModel.reserved = true

        assertEquals(newTableModel, tableModel)
    }

}
