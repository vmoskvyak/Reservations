package com.vmoskvyak.reservations.db.entity

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TableDTOTest {

    private lateinit var tableDTO: TableDTO

    @Before
    fun setUp() {
        tableDTO = TableDTO()
        tableDTO.id = 1
        tableDTO.isReserved = true
    }

    @Test
    fun getId() {
        assertEquals(1L, tableDTO.id)
    }

    @Test
    fun setId() {
        tableDTO.id = 2
        assertEquals(2L, tableDTO.id!!)
    }

    @Test
    fun isReserved() {
        assertTrue(tableDTO.isReserved)
    }

    @Test
    fun setReserved() {
        tableDTO.isReserved = false
        assertFalse(tableDTO.isReserved)
    }

    @Test
    fun tableDTOObject() {
        val newTableDTO = TableDTO()
        newTableDTO.id = 1
        newTableDTO.isReserved = true

        assertEquals(newTableDTO, tableDTO)
    }

}
