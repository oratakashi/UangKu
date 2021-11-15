package com.oratakashi.uangku.domain.model

import androidx.room.PrimaryKey
import com.oratakashi.uangku.data.model.TransactionEntity
import java.util.*

data class Transaction(
    val date: String,
    val nominal: Long,
    val description: String,
    val type: String,
    val idCategory: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) {
    fun toTransactionEntity(): TransactionEntity {
        return TransactionEntity(
            date, nominal, description, type, idCategory, id
        )
    }
}
