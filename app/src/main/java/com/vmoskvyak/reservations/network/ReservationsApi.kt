package com.vmoskvyak.reservations.network

import com.vmoskvyak.reservations.network.model.CustomerModel
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ReservationsApi {

    @GET("customer-list.json")
    fun getCustomerList() : Deferred<Response<List<CustomerModel>>>

    @GET("table-map.json")
    fun getTables(): Deferred<Response<List<Boolean>>>

}
