package com.vmoskvyak.reservations.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.reservations.datasource.mapper.CustomerToCustomerEntityMapper
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import kotlinx.coroutines.experimental.launch

class GetCustomersDataSource(
        reservationsRepository: ReservationsRepository,
        requestStatus: MutableLiveData<String>):
        BaseDataSource<List<CustomerModel>>(reservationsRepository, requestStatus){

    override fun loadData() : LiveData<List<CustomerModel>> {
        val mapper = CustomerToCustomerEntityMapper()
        refreshCustomers(mapper)

        val customerFromLocal : LiveData<List<CustomerDTO>> =
                reservationsRepository.getCustomersFromLocal()

        val mediator = MediatorLiveData<List<CustomerModel>>()
        mediator.addSource(customerFromLocal) {
            mediator.value = it?.let { it1 -> mapper.reverseMap(it1) }
        }

        return mediator
    }

    private fun refreshCustomers(mapper: CustomerToCustomerEntityMapper) {
        launch {
            if (reservationsRepository.hasCustomersLocal()) {
                return@launch
            }

            try {
                val response = reservationsRepository.getCustomers()
                val body = response.body()

                if (!response.isSuccessful || body == null) {
                    requestStatus.postValue(response.message())
                    return@launch
                }

                reservationsRepository.saveCustomersToLocal(mapper.map(body))
            } catch (exception: Exception) {
                requestStatus.postValue("Can't load data. Please check internet connection")
            }
        }
    }

}
