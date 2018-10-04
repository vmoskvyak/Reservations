package com.vmoskvyak.reservations.ui.fragments.customers

import com.vmoskvyak.reservations.network.model.CustomerModel

class CustomerItemViewModel(private val customerModel: CustomerModel) {

    fun getName(): String {
        return customerModel.firstName + " " + customerModel.lastName
    }

}
