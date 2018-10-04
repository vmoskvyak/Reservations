package com.vmoskvyak.reservations.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import kotlinx.coroutines.experimental.launch

class GetCustomersDataSource(
        reservationsRepository: ReservationsRepository,
        requestStatus: MutableLiveData<String>):
        BaseDataSource<List<CustomerModel>>(reservationsRepository, requestStatus){

    override fun loadData() : LiveData<List<CustomerModel>> {
        val result: MutableLiveData<List<CustomerModel>> = MutableLiveData()

        launch {
            val response = reservationsRepository.getCustomers()
            val body = response.body()

            if (!response.isSuccessful || body == null) {
                requestStatus.postValue(response.message())
                return@launch
            }

            result.postValue(body)
        }

        return result
    }

}
