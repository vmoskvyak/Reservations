package com.vmoskvyak.reservations.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.databinding.ActivityMainBinding
import com.vmoskvyak.reservations.manager.ReservationsBroadcastReceiver
import com.vmoskvyak.reservations.ui.fragments.customers.CustomerListFragment
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)

        initToolbar(binding)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_container, CustomerListFragment(), CustomerListFragment.TAG)
                    .commit()
        }

        setupAlarmManager()
    }

    private fun setupAlarmManager() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val broadcastIntent = Intent(this, ReservationsBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
                this, 0, broadcastIntent, 0
        )

        val updateTime = TimeUnit.MINUTES.toMillis(10)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + updateTime,
                updateTime,
                pendingIntent)
    }

    private fun initToolbar(binding: ActivityMainBinding) {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        toolbar?.setNavigationOnClickListener { onBackPressed() }

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    fun setToolbarTitle(title: String) {
        toolbar?.title = title
    }

}
