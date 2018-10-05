package com.vmoskvyak.reservations.datasource.mapper

import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.network.model.CustomerModel

class CustomerToCustomerEntityMapper : Mapper<CustomerModel, CustomerDTO>() {

    override fun reverseMap(value: CustomerDTO): CustomerModel {
        val customerModel = CustomerModel()
        customerModel.id = value.id
        customerModel.firstName = value.firstName
        customerModel.lastName = value.lastName

        return customerModel
    }

    override fun map(value: CustomerModel): CustomerDTO {
        val customerDTO = CustomerDTO()
        customerDTO.id = value.id
        customerDTO.firstName = value.firstName
        customerDTO.lastName = value.lastName

        return customerDTO
    }

}
