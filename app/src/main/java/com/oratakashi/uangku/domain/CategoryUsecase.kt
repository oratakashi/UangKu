package com.oratakashi.uangku.domain

import com.oratakashi.uangku.domain.model.Category
import io.reactivex.Single

interface CategoryUsecase {
    fun add(data: Category): Single<Long>
}