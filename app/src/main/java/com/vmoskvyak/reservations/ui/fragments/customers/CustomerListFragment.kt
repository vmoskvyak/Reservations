package com.vmoskvyak.reservations.ui.fragments.customers

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.databinding.FragmentCustomerListBinding
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.ui.MainActivity
import com.vmoskvyak.reservations.ui.adapters.CustomersAdapter
import com.vmoskvyak.reservations.ui.fragments.tables.TablesFragment
import com.vmoskvyak.reservations.viewmodel.CustomersViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CustomerListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CustomersViewModel

    private val customersAdapter = CustomersAdapter()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCustomerListBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_customer_list, container, false)

        initRecycleView(binding)
        showCustomers()

        return binding.root
    }

    private fun showCustomers() {
        viewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        viewModel.loadCustomers().observe(this, Observer<List<CustomerModel>> {
            it?.let { list -> customersAdapter.setCustomers(list) }
        })

    }

    private fun initRecycleView(binding: FragmentCustomerListBinding) {
        val recyclerView: RecyclerView = binding.rvCustomers

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        initAdapter(recyclerView)
    }

    private fun initAdapter(recyclerView: RecyclerView) {
        customersAdapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick() {
                fragmentManager?.
                        beginTransaction()?.
                        replace(R.id.fl_container,
                                TablesFragment.newInstance(),
                                TablesFragment.TAG)?.
                        addToBackStack(CustomerListFragment.TAG)?.
                        commit()
            }
        }

        recyclerView.adapter = customersAdapter
    }

    companion object {
        const val TAG = "CustomerListFragment"
    }

}
