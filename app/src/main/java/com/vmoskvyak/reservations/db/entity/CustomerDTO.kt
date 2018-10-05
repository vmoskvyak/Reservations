package com.vmoskvyak.reservations.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerDTO (
        @PrimaryKey var id: Long?,
        @ColumnInfo(name = "first_name") var firstName: String,
        @ColumnInfo(name = "last_name") var lastName: String
) {
    constructor(): this(null, "", "")
}
