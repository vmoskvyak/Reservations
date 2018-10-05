package com.vmoskvyak.reservations.datasource.mapper

import com.vmoskvyak.reservations.db.entity.TableDTO

class TablesToTablesEntityMapper : Mapper<Boolean, TableDTO>() {

    override fun map(value: Boolean): TableDTO {
        val tableDTO = TableDTO()
        tableDTO.isReserved = value

        return tableDTO
    }

    override fun reverseMap(value: TableDTO): Boolean {
        return value.isReserved
    }

}
