package com.oratakashi.uangku.domain

import com.oratakashi.uangku.data.TransactionRepository
import com.oratakashi.uangku.domain.model.Transaction
import io.reactivex.Single

class TransactionInteractor(
    private val repository: TransactionRepository
) : TransactionUsecase{
    override fun add(data: Transaction): Single<Long> {
        return repository.add(data.toTransactionEntity())
    }
}