package com.vmoskvyak.reservations.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.vmoskvyak.reservations.datasource.TablesDataSource
import com.vmoskvyak.reservations.network.model.TableModel
import com.vmoskvyak.reservations.testObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TablesViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TablesViewModel

    private val tablesDataSource = Mockito.mock(TablesDataSource::class.java)

    @Before
    fun setUp() {
        viewModel = TablesViewModel(tablesDataSource)
    }

    @Test
    fun loadTables() {
        val dataSourceLiveData = MutableLiveData<List<TableModel>>()
        val list = ArrayList<TableModel>()
        val tableModel = TableModel()

        tableModel.id = 1
        tableModel.reserved = true

        list.add(tableModel)
        dataSourceLiveData.value = list

        Mockito.`when`(tablesDataSource.loadData())
                .thenReturn(dataSourceLiveData)

        val viewModelLiveData = viewModel.loadTables().testObserver()

        Truth.assert_()
                .that(viewModelLiveData.observedValues)
                .isEqualTo(arrayListOf(list))
    }

    @Test
    fun updateTableReservation() {
       viewModel.updateTableReservation(1, true)

        Mockito.verify(tablesDataSource).updateData(1, true)
    }

    @Test
    fun getErrorMessageObserver() {
        Mockito.`when`(tablesDataSource.errorMessageObserver).thenReturn(MutableLiveData())
        val liveData = viewModel.getErrorMessageObserver().testObserver()

        Truth.assert_()
                .that(liveData.observedValues)
                .isEqualTo(emptyList<String>())
    }

}
