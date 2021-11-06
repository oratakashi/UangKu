package com.oratakashi.uangku.data

import com.oratakashi.uangku.data.model.CategoryEntity
import io.reactivex.Single

interface CategoryRepository {
    fun add(data: CategoryEntity): Single<Long>
}