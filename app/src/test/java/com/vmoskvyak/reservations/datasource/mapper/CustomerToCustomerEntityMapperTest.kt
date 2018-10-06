package com.vmoskvyak.reservations.datasource.mapper

import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.network.model.CustomerModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CustomerToCustomerEntityMapperTest {

    private lateinit var customerToCustomerEntityMapper : CustomerToCustomerEntityMapper
    private lateinit var customerModel: CustomerModel
    private lateinit var customerDTO: CustomerDTO

    @Before
    fun setup() {
        customerToCustomerEntityMapper = CustomerToCustomerEntityMapper()

        customerModel = CustomerModel()
        customerModel.id = 1
        customerModel.firstName = "first name"
        customerModel.lastName = "last name"

        customerDTO = CustomerDTO()
        customerDTO.id = 1
        customerDTO.firstName = "first name"
        customerDTO.lastName = "last name"
    }

    @Test
    fun reverseMap() {
        val reverseMap = customerToCustomerEntityMapper.reverseMap(customerDTO)

        assertEquals(customerModel.id, reverseMap.id)
        assertEquals(customerModel.firstName, reverseMap.firstName)
        assertEquals(customerModel.lastName, reverseMap.lastName)
    }

    @Test
    fun map() {
        val map = customerToCustomerEntityMapper.map(customerModel)

        assertEquals(customerDTO.id, map.id)
        assertEquals(customerDTO.firstName, map.firstName)
        assertEquals(customerDTO.lastName, map.lastName)
    }

}
