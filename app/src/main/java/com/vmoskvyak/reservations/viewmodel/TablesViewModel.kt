package com.vmoskvyak.reservations.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.reservations.datasource.TablesDataSource
import com.vmoskvyak.reservations.network.model.TableModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import javax.inject.Inject

class TablesViewModel
@Inject constructor(repository: ReservationsRepository) : ViewModel() {

    val requestStatus: MutableLiveData<String> = MutableLiveData()

    private val tablesDataSource = TablesDataSource(repository, requestStatus)

    fun loadTables() : LiveData<List<TableModel>> {
        return tablesDataSource.loadData()
    }

    fun updateTableReservation(tableId: Long?, reserved: Boolean) {
        tablesDataSource.updateData(tableId, reserved)
    }

}
