package com.oratakashi.uangku.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.oratakashi.uangku.data.model.CategoryEntity
import io.reactivex.Single

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(data: CategoryEntity): Single<Long>

    @Delete
    fun delete(data: CategoryEntity): Single<Int>

    @Query("Select * from category")
    fun getAll(): PagingSource<Int, CategoryEntity>

    @Query("Select * from category where name like :query")
    fun searchByName(query: String): PagingSource<Int, CategoryEntity>

    @Query("Select * from category where type = :type")
    fun getAllByType(type: String): PagingSource<Int, CategoryEntity>

    @Query("Select * from category where name like :query and type = :type")
    fun searchByNameAndType(query: String, type: String): PagingSource<Int, CategoryEntity>
}