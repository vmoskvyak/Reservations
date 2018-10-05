package com.vmoskvyak.reservations.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "table_reservations")
data class TableDTO(
        @PrimaryKey(autoGenerate = true) var id: Long?,
        @ColumnInfo(name = "is_reserved") var isReserved: Boolean
) {
    constructor() : this(null, false)

}
