package com.vmoskvyak.reservations.datasource

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.model.TableModel
import com.vmoskvyak.reservations.repository.ReservationsRepository
import com.vmoskvyak.reservations.testObserver
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TablesDataSourceTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var tablesDataSource: TablesDataSource

    private val reservationsRepository = Mockito.mock(ReservationsRepository::class.java)

    @Before
    fun setUp() {
        tablesDataSource = TablesDataSource(reservationsRepository, MutableLiveData())
    }

    @Test
    fun updateData() {
        tablesDataSource.updateData(1, true)
        Mockito.verify(reservationsRepository).updateTableReservation(1, true)
    }

    @Test
    fun loadData() {
        runBlocking {
            val dtoLiveData: MutableLiveData<List<TableDTO>> = MutableLiveData()
            val dtoList: ArrayList<TableDTO> = ArrayList()

            val tableDTO = TableDTO()
            tableDTO.isReserved = true
            dtoList.add(tableDTO)
            dtoLiveData.value = dtoList

            Mockito.`when`(reservationsRepository.getTablesFromLocal())
                    .thenReturn(dtoLiveData)
            val liveData = tablesDataSource.loadData().testObserver()

            val resultList: ArrayList<TableModel> = ArrayList()

            val tableModel = TableModel()
            tableModel.id = null
            tableModel.reserved = true

            resultList.add(tableModel)

            Truth.assert_()
                    .that(liveData.observedValues)
                    .isEqualTo(arrayListOf(resultList))
        }
    }

    @Test
    fun resetData() {
        tablesDataSource.resetData()
        Mockito.verify(reservationsRepository).clearReservations()
    }

    @Test
    fun getErrorMessageObserver() {
        val liveData = tablesDataSource.errorMessageObserver.testObserver()

        Truth.assert_()
                .that(liveData.observedValues)
                .isEqualTo(emptyList<String>())
    }

}
