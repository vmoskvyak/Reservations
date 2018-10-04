package com.vmoskvyak.reservations.network.model

import com.google.gson.annotations.SerializedName

class CustomerModel {

    @SerializedName("customerFirstName")
    var firstName: String = ""

    @SerializedName("customerLastName")
    var lastName: String = ""

    @SerializedName("id")
    var id: Long = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CustomerModel

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }

}
