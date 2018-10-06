package com.vmoskvyak.reservations.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.vmoskvyak.reservations.datasource.CustomersDataSource
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.testObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class CustomersViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CustomersViewModel

    private val customersDataSource = mock(CustomersDataSource::class.java)

    @Before
    fun setUp() {
        viewModel = CustomersViewModel(customersDataSource)
    }

    @Test
    fun loadCustomers() {
        val dataSourceLiveData = MutableLiveData<List<CustomerModel>>()
        val list = ArrayList<CustomerModel>()
        val customerModel = CustomerModel()

        customerModel.id = 1
        customerModel.firstName = "first name"
        customerModel.lastName = "last name"

        list.add(customerModel)
        dataSourceLiveData.value = list

        Mockito.`when`(customersDataSource.loadData())
                .thenReturn(dataSourceLiveData)

        val viewModelLiveData = viewModel.loadCustomers().testObserver()

        Truth.assert_()
                .that(viewModelLiveData.observedValues)
                .isEqualTo(arrayListOf(list))
    }

    @Test
    fun getErrorMessageObserver() {
        Mockito.`when`(customersDataSource.errorMessageObserver).thenReturn(MutableLiveData())
        val liveData = viewModel.getErrorMessageObserver().testObserver()

        Truth.assert_()
                .that(liveData.observedValues)
                .isEqualTo(emptyList<String>())
    }

}
