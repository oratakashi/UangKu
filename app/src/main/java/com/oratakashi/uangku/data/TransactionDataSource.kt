package com.oratakashi.uangku.data

import com.oratakashi.uangku.data.db.dao.TransactionDao
import com.oratakashi.uangku.data.model.TransactionEntity
import io.reactivex.Single

class TransactionDataSource(
    private val transaction: TransactionDao
) : TransactionRepository {
    override fun add(data: TransactionEntity): Single<Long> {
        return transaction.add(data)
    }
}