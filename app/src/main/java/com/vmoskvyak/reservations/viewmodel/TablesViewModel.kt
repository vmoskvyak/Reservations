package com.vmoskvyak.reservations.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.reservations.datasource.GetTablesDataSource
import com.vmoskvyak.reservations.repository.ReservationsRepository
import javax.inject.Inject

class TablesViewModel
@Inject constructor(private val repository: ReservationsRepository) : ViewModel() {

    val requestStatus: MutableLiveData<String> = MutableLiveData()

    fun loadTables() : LiveData<List<Boolean>> {
        val getCustomersDataSource = GetTablesDataSource(repository, requestStatus)

        return getCustomersDataSource.loadData()
    }

}
