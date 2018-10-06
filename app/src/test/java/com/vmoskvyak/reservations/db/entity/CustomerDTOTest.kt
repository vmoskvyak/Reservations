package com.vmoskvyak.reservations.db.entity

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CustomerDTOTest {

    private lateinit var customerDTO: CustomerDTO

    @Before
    fun setUp() {
        customerDTO = CustomerDTO(1, "first name", "last name")
    }

    @Test
    fun getId() {
        assertEquals(1L, customerDTO.id)
    }

    @Test
    fun setId() {
        customerDTO.id = 2
        assertEquals(2L, customerDTO.id!!)
    }

    @Test
    fun getFirstName() {
        assertEquals("first name", customerDTO.firstName)
    }

    @Test
    fun setFirstName() {
        customerDTO.firstName = "new first name"
        assertEquals("new first name", customerDTO.firstName)
    }

    @Test
    fun getLastName() {
        assertEquals("last name", customerDTO.lastName)
    }

    @Test
    fun setLastName() {
        customerDTO.lastName = "new last name"
        assertEquals("new last name", customerDTO.lastName)
    }

    @Test
    fun customerDTOObject() {
        val newCustomerDTO = CustomerDTO(1, "first name", "last name")
        assertEquals(newCustomerDTO, customerDTO)
    }

}
