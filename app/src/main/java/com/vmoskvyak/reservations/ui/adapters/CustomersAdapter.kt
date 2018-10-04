package com.vmoskvyak.reservations.ui.adapters

import android.databinding.DataBindingUtil
import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vmoskvyak.reservations.databinding.CustomerItemBinding
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.ui.fragments.customers.CustomerItemViewModel
import com.vmoskvyak.reservations.ui.fragments.customers.OnItemClickListener

class CustomersAdapter(private val comparator: Comparator<CustomerModel>) :
        RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder>() {

    var customersList: MutableList<CustomerModel> = ArrayList()
    private var sortedCustomersList: SortedList<CustomerModel>? = null

    init {
        initSortedList()
    }

    var onItemClickListener: OnItemClickListener? = null

    fun setCustomers(newCustomers: List<CustomerModel>) {
        customersList.clear()
        customersList.addAll(newCustomers)

        sortedCustomersList?.clear()
        sortedCustomersList?.addAll(newCustomers)

        notifyDataSetChanged()
    }

    fun replaceAllData(models: List<CustomerModel>) {
        sortedCustomersList?.beginBatchedUpdates()

        val size = sortedCustomersList?.size() ?: 0

        for (i in size - 1 downTo 0) {
            val model = sortedCustomersList?.get(i)
            if (!models.contains(model)) {
                sortedCustomersList?.remove(model)
            }
        }
        sortedCustomersList?.addAll(models)
        sortedCustomersList?.endBatchedUpdates()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CustomerItemBinding.inflate(inflater, parent, false)
        return CustomerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return sortedCustomersList?.size() ?: 0
    }

    override fun onBindViewHolder(holderData: CustomerViewHolder, position: Int) {
        val customer = sortedCustomersList?.get(position)

        holderData.binding?.viewModel = customer?.let { CustomerItemViewModel(it) }
        holderData.binding?.click = object : OnCustomerClickListener {
            override fun onCustomerClick(customerItemViewModel: CustomerItemViewModel) {
                onItemClickListener?.onItemClick()
            }
        }
    }

    private fun initSortedList() {
        sortedCustomersList = SortedList<CustomerModel>(CustomerModel::class.java,
                object : SortedList.Callback<CustomerModel>() {
            override fun compare(a: CustomerModel, b: CustomerModel): Int {
                return comparator.compare(a, b)
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

            override fun areContentsTheSame(oldItem: CustomerModel,
                                            newItem: CustomerModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(item1: CustomerModel, item2: CustomerModel): Boolean {
                return item1 === item2
            }
        })
    }

    interface OnCustomerClickListener {

        fun onCustomerClick(customerItemViewModel: CustomerItemViewModel)

    }

    class CustomerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: CustomerItemBinding? = DataBindingUtil.bind(view)

    }

}
