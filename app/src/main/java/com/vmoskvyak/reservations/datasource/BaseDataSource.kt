package com.vmoskvyak.reservations.datasource

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.vmoskvyak.reservations.repository.ReservationsRepository

abstract class BaseDataSource<T>(
        protected val reservationsRepository: ReservationsRepository,
        protected var requestStatus: MutableLiveData<String>) {

    abstract fun loadData() : LiveData<T>
}
