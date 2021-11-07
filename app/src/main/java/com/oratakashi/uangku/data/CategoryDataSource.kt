package com.oratakashi.uangku.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.oratakashi.uangku.data.db.dao.CategoryDao
import com.oratakashi.uangku.data.model.CategoryEntity
import io.reactivex.Single

class CategoryDataSource(
    private val category: CategoryDao
) : CategoryRepository {
    override fun add(data: CategoryEntity): Single<Long> {
        return category.add(data)
    }

    override fun delete(data: CategoryEntity): Single<Int> {
        return category.delete(data)
    }

    override fun getAll(): LiveData<PagingData<CategoryEntity>> {
        return Pager(
            config = PagingConfig(
                20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { category.getAll() }
        ).liveData
    }

    override fun getByType(type: String): LiveData<PagingData<CategoryEntity>> {
        return Pager(
            config = PagingConfig(
                20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { category.getAllByType(type) }
        ).liveData
    }

    override fun searchByName(name: String): LiveData<PagingData<CategoryEntity>> {
        return Pager(
            config = PagingConfig(
                20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { category.searchByName("%${name}%") }
        ).liveData
    }

    override fun searchByNameAndType(
        name: String,
        type: String
    ): LiveData<PagingData<CategoryEntity>> {
        return Pager(
            config = PagingConfig(
                20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { category.searchByNameAndType("%$name%", type) }
        ).liveData
    }
}