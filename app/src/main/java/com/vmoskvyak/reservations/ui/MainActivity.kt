package com.vmoskvyak.reservations.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.databinding.ActivityMainBinding
import com.vmoskvyak.reservations.ui.fragments.customers.CustomerListFragment

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

    fun showErrorDialog(message: String?) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(R.string.error)
        alertDialog.setMessage(message)
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                getString(android.R.string.ok)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

}
