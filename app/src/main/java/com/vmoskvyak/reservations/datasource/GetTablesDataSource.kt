package com.vmoskvyak.reservations.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.reservations.repository.ReservationsRepository
import kotlinx.coroutines.experimental.launch

class GetTablesDataSource(
        reservationsRepository: ReservationsRepository,
        requestStatus: MutableLiveData<String>) :
        BaseDataSource<List<Boolean>>(reservationsRepository, requestStatus) {

    override fun loadData(): LiveData<List<Boolean>> {
        val result: MutableLiveData<List<Boolean>> = MutableLiveData()

        launch {
            val response = reservationsRepository.getTables()
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
