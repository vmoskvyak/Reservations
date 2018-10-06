package com.vmoskvyak.reservations.datasource.mapper

import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.model.TableModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TablesToTablesEntityMapperTest {

    private lateinit var tablesToTablesEntityMapper: TablesToTablesEntityMapper
    private lateinit var tableModel: TableModel
    private lateinit var tableDTO: TableDTO

    @Before
    fun setUp() {
        tablesToTablesEntityMapper = TablesToTablesEntityMapper()

        tableModel = TableModel()
        tableModel.id = 1
        tableModel.reserved = true

        tableDTO = TableDTO()
        tableDTO.id = 1
        tableDTO.isReserved = true
    }

    @Test
    fun map() {
        val map = tablesToTablesEntityMapper.map(tableModel)
        assertEquals(tableDTO.isReserved, map.isReserved)
    }

    @Test
    fun reverseMap() {
        val reverseMap = tablesToTablesEntityMapper.reverseMap(tableDTO)
        assertEquals(tableModel.id, reverseMap.id)
        assertEquals(tableModel.reserved, reverseMap.reserved)
    }

}
