package com.oratakashi.uangku.domain.model

import com.oratakashi.uangku.data.model.CategoryEntity
import java.util.*

data class Category(
    val name: String,
    val type: String,
    val id: String = UUID.randomUUID().toString()
) {
    fun toCategoryEntity(): CategoryEntity {
        return CategoryEntity(name, type, id)
    }
}
