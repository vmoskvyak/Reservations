package com.vmoskvyak.reservations.ui.fragments.tables

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.R
import com.vmoskvyak.reservations.databinding.FragmentTablesBinding
import com.vmoskvyak.reservations.ui.MainActivity
import com.vmoskvyak.reservations.ui.adapters.TablesAdapter
import com.vmoskvyak.reservations.viewmodel.TablesViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TablesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: TablesViewModel

    private val adapter = TablesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTablesBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_tables, container, false)

        initRecycleView(binding)
        showTables()

        return binding.root
    }

    private fun showTables() {
        viewModel.requestStatus.observe(this, Observer<String> {
            (activity as MainActivity).showErrorDialog(it)
        })

        viewModel.loadTables().observe(this, Observer<List<Boolean>> {
            it?.let { list -> adapter.setTableReservations(list) }
        })
    }

    private fun initRecycleView(binding: FragmentTablesBinding) {
        val recyclerView: RecyclerView = binding.rvTables

        recyclerView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager

        recyclerView.adapter = adapter
    }

    companion object {
        const val TAG = "TablesFragment"

        fun newInstance(): TablesFragment {
            return TablesFragment()
        }
    }

}
