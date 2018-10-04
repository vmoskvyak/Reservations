package com.vmoskvyak.reservations.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.databinding.TableItemBinding
import com.vmoskvyak.reservations.ui.fragments.tables.TableItemViewModel


class TablesAdapter : RecyclerView.Adapter<TablesAdapter.TableViewHolder>() {

    private var tableReservations: MutableList<Boolean> = ArrayList()

    fun setTableReservations(newReservations: List<Boolean>) {
        tableReservations.clear()
        tableReservations.addAll(newReservations)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = TableItemBinding.inflate(inflater, parent, false)
        return TableViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return tableReservations.count()
    }

    override fun onBindViewHolder(holderData: TableViewHolder, position: Int) {
        val isReserved = tableReservations[position]

        holderData.binding?.viewModel = TableItemViewModel(isReserved)
    }

    class TableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: TableItemBinding? = DataBindingUtil.bind(view)

    }

}
