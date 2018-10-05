package com.vmoskvyak.reservations.di.mudules

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.vmoskvyak.reservations.datasource.CustomersDataSource
import com.vmoskvyak.reservations.datasource.TablesDataSource
import com.vmoskvyak.reservations.di.scopes.AppScope
import com.vmoskvyak.reservations.repository.ReservationsRepository
import com.vmoskvyak.reservations.repository.ReservationsRepositoryImpl
import com.vmoskvyak.reservations.viewmodel.CustomersViewModel
import com.vmoskvyak.reservations.viewmodel.TablesViewModel
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @AppScope
    @Provides
    fun provideErrorMessageObserver() : MutableLiveData<String> {
        return MutableLiveData()
    }

    @AppScope
    @Provides
    fun provideCustomersDataSource(
            reservationsRepository: ReservationsRepository,
            errorMessageObserver: MutableLiveData<String>) : CustomersDataSource {
        return CustomersDataSource(reservationsRepository, errorMessageObserver)
    }

    @AppScope
    @Provides
    fun provideTablesDataSource(
            reservationsRepository: ReservationsRepository,
            errorMessageObserver: MutableLiveData<String>) : TablesDataSource {
        return TablesDataSource(reservationsRepository, errorMessageObserver)
    }

    @AppScope
    @Provides
    fun provideReservationsRepository(reservationsRepository: ReservationsRepositoryImpl):
            ReservationsRepository {
        return reservationsRepository
    }

    @AppScope
    @Provides
    fun provideCustomersViewModel(viewModel: CustomersViewModel): ViewModel {
        return viewModel
    }

    @AppScope
    @Provides
    fun provideTablesViewModel(viewModel: TablesViewModel): ViewModel {
        return viewModel
    }

}
