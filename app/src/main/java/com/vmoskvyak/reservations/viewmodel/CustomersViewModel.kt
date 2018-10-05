package com.vmoskvyak.reservations.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.reservations.datasource.CustomersDataSource
import com.vmoskvyak.reservations.network.model.CustomerModel
import javax.inject.Inject

class CustomersViewModel
@Inject constructor(private val customersDataSource: CustomersDataSource) : ViewModel() {

    fun loadCustomers() : LiveData<List<CustomerModel>> {
        return customersDataSource.loadData()
    }

    fun getErrorMessageObserver() : LiveData<String> {
        return customersDataSource.errorMessageObserver
    }

}
