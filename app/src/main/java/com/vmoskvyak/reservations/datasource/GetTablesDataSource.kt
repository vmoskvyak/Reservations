package com.vmoskvyak.reservations.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.reservations.datasource.mapper.TablesToTablesEntityMapper
import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.repository.ReservationsRepository
import kotlinx.coroutines.experimental.launch

class GetTablesDataSource(
        reservationsRepository: ReservationsRepository,
        requestStatus: MutableLiveData<String>) :
        BaseDataSource<List<Boolean>>(reservationsRepository, requestStatus) {

    override fun loadData(): LiveData<List<Boolean>> {
        val mapper = TablesToTablesEntityMapper()
        refreshTables(mapper)

        val tableDTO: LiveData<List<TableDTO>> = reservationsRepository.getTablesFromLocal()

        val mediator = MediatorLiveData<List<Boolean>>()
        mediator.addSource(tableDTO) {
            mediator.value = it?.let { it1 -> mapper.reverseMap(it1) }
        }

        return mediator
    }

    private fun refreshTables(mapper: TablesToTablesEntityMapper) {
        launch {
            if (reservationsRepository.hasTablesLocal()) {
                return@launch
            }

            try {
                val response = reservationsRepository.getTables()
                val body = response.body()

                if (!response.isSuccessful || body == null) {
                    requestStatus.postValue(response.message())
                    return@launch
                }

                reservationsRepository.saveTablesToLocal(mapper.map(body))
            } catch (exception: Exception) {
                requestStatus.postValue("Can't load data. Please check internet connection")
            }
        }
    }

}
