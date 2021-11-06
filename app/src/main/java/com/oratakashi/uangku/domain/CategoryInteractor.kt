package com.oratakashi.uangku.domain

import com.oratakashi.uangku.data.CategoryRepository
import com.oratakashi.uangku.domain.model.Category
import io.reactivex.Single

class CategoryInteractor(
    private val repository: CategoryRepository
): CategoryUsecase {
    override fun add(data: Category): Single<Long> {
        return repository.add(data.toCategoryEntity())
    }
}