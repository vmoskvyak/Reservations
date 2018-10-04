package com.vmoskvyak.reservations.ui.fragments.tables

import com.vmoskvyak.reservations.R

class TableItemViewModel(private val isReserved: Boolean) {

    fun getImgDrawableId() : Int {
        return if (isReserved) R.drawable.table_reserved else R.drawable.table_free
    }

}
