package com.vmoskvyak.reservations.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.databinding.CustomerItemBinding
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.ui.fragments.customers.CustomerItemViewModel
import com.vmoskvyak.reservations.ui.fragments.customers.OnItemClickListener

class CustomersAdapter : RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder>() {

    private var customers: MutableList<CustomerModel> = ArrayList()

    var onItemClickListener: OnItemClickListener? = null

    fun setCustomers(newCustomers : List<CustomerModel>) {
        customers.clear()
        customers.addAll(newCustomers)

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CustomerItemBinding.inflate(inflater, parent, false)
        return CustomerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return customers.count()
    }

    override fun onBindViewHolder(holderData: CustomerViewHolder, position: Int) {
        val customer = customers[position]

        holderData.binding?.viewModel = CustomerItemViewModel(customer)
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

}
