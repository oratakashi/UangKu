package com.oratakashi.uangku.data

import com.oratakashi.uangku.data.db.dao.CategoryDao
import com.oratakashi.uangku.data.model.CategoryEntity
import io.reactivex.Single

class CategoryDataSource(
    private val category: CategoryDao
): CategoryRepository {
    override fun add(data: CategoryEntity): Single<Long> {
        return category.add(data)
    }
}