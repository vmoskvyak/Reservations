package com.vmoskvyak.reservations.ui.fragments.tables

import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.network.model.TableModel

class TableItemViewModel(private val tableModel: TableModel) {

    fun getImgDrawableId() : Int {
        return if (tableModel.reserved) R.drawable.table_reserved else R.drawable.table_free
    }

    fun getReserved() : Boolean {
        return tableModel.reserved
    }

    fun getTableId() : Long? {
        return tableModel.id
    }

}
