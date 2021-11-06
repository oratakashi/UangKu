package com.oratakashi.uangku.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.oratakashi.uangku.data.model.CategoryEntity
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: CategoryEntity): Single<Long>
}