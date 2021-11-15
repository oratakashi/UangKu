package com.oratakashi.uangku.domain

import com.oratakashi.uangku.domain.model.Transaction
import io.reactivex.Single

interface TransactionUsecase {
    fun add(data: Transaction): Single<Long>
}