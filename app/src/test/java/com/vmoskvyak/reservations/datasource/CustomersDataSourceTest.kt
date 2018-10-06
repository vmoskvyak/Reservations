package com.vmoskvyak.reservations.datasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.vmoskvyak.reservations.db.entity.CustomerDTO
import com.vmoskvyak.reservations.network.model.CustomerModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import com.vmoskvyak.reservations.testObserver
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class CustomersDataSourceTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var customersDataSource: CustomersDataSource

    private val reservationsRepository = mock(ReservationsRepository::class.java)

    @Before
    fun setup() {
        customersDataSource =
                CustomersDataSource(reservationsRepository, MutableLiveData())
    }

    @Test
    fun loadData() {
        runBlocking {
            val dtoLiveData: MutableLiveData<List<CustomerDTO>> = MutableLiveData()
            val dtoList: ArrayList<CustomerDTO> = ArrayList()

            dtoList.add(CustomerDTO(1, "first name", "last name"))
            dtoLiveData.value = dtoList

            Mockito.`when`(reservationsRepository.getCustomersFromLocal())
                    .thenReturn(dtoLiveData)
            val liveData = customersDataSource.loadData().testObserver()

            val resultList: ArrayList<CustomerModel> = ArrayList()

            val customerModel = CustomerModel()
            customerModel.id = 1
            customerModel.firstName = "first name"
            customerModel.lastName = "last name"

            resultList.add(customerModel)

            Truth.assert_()
                    .that(liveData.observedValues)
                    .isEqualTo(arrayListOf(resultList))
        }
    }

    @Test
    fun resetData() {
        customersDataSource.resetData()
        verify(reservationsRepository).clearCustomers()
    }

    @Test
    fun getErrorMessageObserver() {

        val liveData = customersDataSource.errorMessageObserver.testObserver()

        Truth.assert_()
                .that(liveData.observedValues)
                .isEqualTo(emptyList<String>())
    }

}
