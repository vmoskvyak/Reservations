package com.vmoskvyak.reservations.repository

import com.vmoskvyak.reservations.network.ReservationsApi
import com.vmoskvyak.reservations.network.model.CustomerModel
import retrofit2.Response
import javax.inject.Inject

class ReservationsRepositoryImpl
    @Inject constructor(private val reservationsApi: ReservationsApi): ReservationsRepository {

    override suspend fun getCustomers(): Response<List<CustomerModel>> {
        return reservationsApi.getCustomerList().await()
    }

    override suspend fun getTables(): Response<List<Boolean>> {
        return reservationsApi.getTables().await()
    }

}
