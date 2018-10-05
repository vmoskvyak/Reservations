package com.vmoskvyak.reservations.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.reservations.datasource.mapper.TablesToTablesEntityMapper
import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.model.TableModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class TablesDataSource
@Inject constructor(
        private val reservationsRepository: ReservationsRepository,
        val errorMessageObserver: MutableLiveData<String>) {

    fun updateData(tableId: Long?, reserved: Boolean) {
        launch {
            reservationsRepository.updateTableReservation(tableId, reserved)
        }
    }

    fun loadData(): LiveData<List<TableModel>> {
        val mapper = TablesToTablesEntityMapper()
        refreshTables(mapper)

        val tableDTO: LiveData<List<TableDTO>> = reservationsRepository.getTablesFromLocal()

        val mediator = MediatorLiveData<List<TableModel>>()
        mediator.addSource(tableDTO) {
            mediator.value = it?.let { it1 -> mapper.reverseMap(it1) }
        }

        return mediator
    }

    fun resetData() {
        launch {
            reservationsRepository.clearReservations()
            loadData()
        }
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
                    errorMessageObserver.postValue(response.message())
                    return@launch
                }

                val tableModelList = ArrayList<TableModel>(body.size)

                body.forEach {
                    val element = TableModel()
                    element.reserved = it
                    tableModelList.add(element) }

                reservationsRepository.saveTablesToLocal(mapper.map(tableModelList))
            } catch (exception: Exception) {
                errorMessageObserver
                        .postValue("Can't load data. Please check internet connection")
            }
        }
    }

}
