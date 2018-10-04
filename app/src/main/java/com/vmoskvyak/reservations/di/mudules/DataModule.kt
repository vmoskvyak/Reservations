package com.vmoskvyak.reservations.di.mudules

import android.arch.lifecycle.ViewModel
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
