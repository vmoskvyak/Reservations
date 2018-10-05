package com.vmoskvyak.reservations.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.databinding.TableItemBinding
import com.vmoskvyak.reservations.network.model.TableModel
import com.vmoskvyak.reservations.ui.fragments.tables.OnTableItemClickListener
import com.vmoskvyak.reservations.ui.fragments.tables.TableItemViewModel

class TablesAdapter :
       ListAdapter<TableModel, TablesAdapter.TableViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener: OnTableItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TableViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = TableItemBinding.inflate(inflater, parent, false)
        return TableViewHolder(binding.root)
    }

    override fun onBindViewHolder(holderData: TableViewHolder, position: Int) {
        val isReserved = getItem(position)

        holderData.binding?.viewModel = TableItemViewModel(isReserved)
        holderData.binding?.click = object : OnTableClickListener {
            override fun onTableClick(tableItemViewModel: TableItemViewModel) {
                onItemClickListener?.onItemClick(tableItemViewModel.getTableId(),
                        tableItemViewModel.getReserved())
            }
        }
    }

    interface OnTableClickListener {

        fun onTableClick(tableItemViewModel: TableItemViewModel)

    }

    class TableViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: TableItemBinding? = DataBindingUtil.bind(view)

    }

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<TableModel> =
                object : DiffUtil.ItemCallback<TableModel>() {
                    override fun areItemsTheSame(oldItem: TableModel,
                                                 newItem: TableModel): Boolean {
                        return oldItem.id == newItem.id
                    }

                    override fun areContentsTheSame(oldItem: TableModel,
                                                    newItem: TableModel): Boolean {
                        return oldItem == newItem
                    }
                }
    }

}
