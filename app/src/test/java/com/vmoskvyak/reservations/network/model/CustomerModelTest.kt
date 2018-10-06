package com.vmoskvyak.reservations.network.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CustomerModelTest {

    private lateinit var customerModel: CustomerModel

    @Before
    fun setup() {
        customerModel = CustomerModel()
        customerModel.id = 1
        customerModel.firstName = "first name"
        customerModel.lastName = "last name"
    }

    @Test
    fun getFirstName() {
        assertEquals("first name", customerModel.firstName)
    }

    @Test
    fun setFirstName() {
        customerModel.firstName = "new first name"
        assertEquals("new first name", customerModel.firstName)
    }

    @Test
    fun getLastName() {
        assertEquals("last name", customerModel.lastName)
    }

    @Test
    fun setLastName() {
        customerModel.lastName = "new last name"
        assertEquals("new last name", customerModel.lastName)
    }

    @Test
    fun getId() {
        assertEquals(1L, customerModel.id)
    }

    @Test
    fun setId() {
        customerModel.id = 2
        assertEquals(2, customerModel.id!!)
    }

    @Test
    fun customerModelObject() {
        val newCustomerModel = CustomerModel()
        newCustomerModel.id = 1
        newCustomerModel.firstName = "first name"
        newCustomerModel.lastName = "last name"

        assertEquals(newCustomerModel, customerModel)
    }

}
