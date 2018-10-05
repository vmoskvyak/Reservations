package com.vmoskvyak.reservations.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.databinding.CustomerItemBinding
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.ui.fragments.customers.CustomerItemViewModel
import com.vmoskvyak.reservations.ui.fragments.customers.OnCustomerItemClickListener

class CustomersAdapter :
        ListAdapter<CustomerModel, CustomersAdapter.CustomerViewHolder>(DIFF_CALLBACK) {

    var onItemClickListener: OnCustomerItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CustomerItemBinding.inflate(inflater, parent, false)
        return CustomerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holderData: CustomerViewHolder, position: Int) {
        val customer = getItem(position)

        holderData.binding?.viewModel = customer?.let { CustomerItemViewModel(it) }
        holderData.binding?.click = object : OnCustomerClickListener {
            override fun onCustomerClick(customerItemViewModel: CustomerItemViewModel) {
                onItemClickListener?.onItemClick()
            }
        }
    }

    interface OnCustomerClickListener {

        fun onCustomerClick(customerItemViewModel: CustomerItemViewModel)

    }

    class CustomerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: CustomerItemBinding? = DataBindingUtil.bind(view)

    }

    companion object {
        var DIFF_CALLBACK: DiffUtil.ItemCallback<CustomerModel> =
                object : DiffUtil.ItemCallback<CustomerModel>() {
                    override fun areItemsTheSame(oldItem: CustomerModel,
                                                 newItem: CustomerModel): Boolean {
                        return oldItem.id == newItem.id
                    }

                    override fun areContentsTheSame(oldItem: CustomerModel,
                                                    newItem: CustomerModel): Boolean {
                        return oldItem == newItem
                    }
                }
    }

}
