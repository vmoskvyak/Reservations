package com.vmoskvyak.reservations.network.model

import com.google.gson.annotations.SerializedName

class CustomerModel {

    @SerializedName("customerFirstName")
    var firstName: String = ""

    @SerializedName("customerLastName")
    var lastName: String = ""

    @SerializedName("id")
    var id: Long = 0

}
