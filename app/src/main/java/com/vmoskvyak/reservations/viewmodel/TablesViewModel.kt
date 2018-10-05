package com.vmoskvyak.reservations.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.reservations.datasource.TablesDataSource
import com.vmoskvyak.reservations.network.model.TableModel
import javax.inject.Inject

class TablesViewModel
@Inject constructor(private val tablesDataSource: TablesDataSource) : ViewModel() {

    fun loadTables() : LiveData<List<TableModel>> {
        return tablesDataSource.loadData()
    }

    fun updateTableReservation(tableId: Long?, reserved: Boolean) {
        tablesDataSource.updateData(tableId, reserved)
    }

    fun getErrorMessageObserver() : LiveData<String> {
        return tablesDataSource.errorMessageObserver
    }

}
