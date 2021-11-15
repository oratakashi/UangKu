package com.oratakashi.uangku.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oratakashi.uangku.domain.model.Transaction
import java.util.*

@Entity(tableName = "Transaction")
data class TransactionEntity(
    var date: String,
    var nominal: Long,
    var description: String,
    var type: String,
    var idCategory: String,
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
) {
    fun toTransaction(): Transaction {
        return Transaction(
            date, nominal, description, type, idCategory, id
        )
    }
}
