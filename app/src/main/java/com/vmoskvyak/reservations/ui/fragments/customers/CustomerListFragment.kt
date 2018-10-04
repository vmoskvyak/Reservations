package com.vmoskvyak.reservations.ui.fragments.customers

import android.app.SearchManager
import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.databinding.FragmentCustomerListBinding
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.ui.MainActivity
import com.vmoskvyak.reservations.ui.adapters.CustomersAdapter
import com.vmoskvyak.reservations.ui.fragments.tables.TablesFragment
import com.vmoskvyak.reservations.viewmodel.CustomersViewModel
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

class CustomerListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: CustomersViewModel

    private var searchView: SearchView? = null
    private var recyclerView: RecyclerView? = null

    private val customersAdapter = CustomersAdapter(getAlphabeticalComparator())

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCustomerListBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_customer_list, container, false)
        setHasOptionsMenu(true)

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
        recyclerView = binding.rvCustomers

        recyclerView?.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager

        initAdapter()
    }

    private fun initAdapter() {
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

        recyclerView?.adapter = customersAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = searchItem?.actionView as SearchView

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        searchView?.isIconified = true
        searchView?.onActionViewExpanded()

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val customersList = customersAdapter.customersList
                if (newText.isEmpty()) {
                    customersAdapter.replaceAllData(customersList)
                    recyclerView?.scrollToPosition(0)
                    return true
                }

                val filter = customersList
                        .filter { it ->
                            val fullName = it.firstName.toLowerCase() + " " +
                                    it.lastName.toLowerCase()
                            fullName.contains(newText) }
                customersAdapter.replaceAllData(filter)
                recyclerView?.scrollToPosition(0)

                return true
            }
        })
    }

    companion object {
        const val TAG = "CustomerListFragment"

        private fun getAlphabeticalComparator(): Comparator<CustomerModel> {
            return Comparator { a, b ->
                a.firstName.toLowerCase().compareTo(b.firstName.toLowerCase()) }
        }
    }

}
