package com.vmoskvyak.reservations.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.reservations.datasource.GetCustomersDataSource
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import javax.inject.Inject

class CustomersViewModel
@Inject constructor(private val repository: ReservationsRepository) : ViewModel() {

    val requestStatus: MutableLiveData<String> = MutableLiveData()

    fun loadCustomers() : LiveData<List<CustomerModel>> {
        val getCustomersDataSource = GetCustomersDataSource(repository, requestStatus)

        return getCustomersDataSource.loadData()
    }

}
