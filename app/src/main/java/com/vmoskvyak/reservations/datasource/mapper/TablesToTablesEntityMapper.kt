package com.vmoskvyak.reservations.datasource.mapper

import com.vmoskvyak.reservations.db.entity.TableDTO
import com.vmoskvyak.reservations.network.model.TableModel

class TablesToTablesEntityMapper : Mapper<TableModel, TableDTO>() {

    override fun map(value: TableModel): TableDTO {
        val tableDTO = TableDTO()
        tableDTO.isReserved = value.reserved

        return tableDTO
    }

    override fun reverseMap(value: TableDTO): TableModel {
        val tableModel = TableModel()
        tableModel.id = value.id
        tableModel.reserved = value.isReserved

        return tableModel
    }

}
