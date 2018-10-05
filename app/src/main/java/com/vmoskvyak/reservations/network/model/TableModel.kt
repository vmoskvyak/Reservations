package com.vmoskvyak.reservations.network.model

class TableModel {

    var id: Long? = 0

    var reserved: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TableModel

        if (id != other.id) return false
        if (reserved != other.reserved) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + reserved.hashCode()
        return result
    }

}
