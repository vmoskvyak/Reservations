package com.vmoskvyak.reservations.repository

import com.vmoskvyak.reservations.network.model.CustomerModel
import retrofit2.Response

interface ReservationsRepository {

    suspend fun getCustomers() : Response<List<CustomerModel>>

    suspend fun getTables() : Response<List<Boolean>>

}
