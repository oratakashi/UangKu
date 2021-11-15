package com.oratakashi.uangku.data

import com.oratakashi.uangku.data.model.TransactionEntity
import io.reactivex.Single

interface TransactionRepository {
    fun add(data: TransactionEntity): Single<Long>
}