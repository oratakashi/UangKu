package com.oratakashi.uangku.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oratakashi.uangku.domain.model.Category
import java.util.*

@Entity(tableName = "Category")
data class CategoryEntity(
    var name: String,
    var type: String,
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
) {
    fun toCategory(): Category {
        return Category(name, type, id)
    }
}