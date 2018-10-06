package com.vmoskvyak.reservations.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.vmoskvyak.reservations.datasource.CustomersDataSource
import com.vmoskvyak.reservations.datasource.TablesDataSource
import dagger.android.AndroidInjection
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ReservationsBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var customersDataSource: CustomersDataSource

    @Inject
    lateinit var tablesDataSource: TablesDataSource

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)

        launch {
            customersDataSource.resetData()
            tablesDataSource.resetData()
        }
    }

}
