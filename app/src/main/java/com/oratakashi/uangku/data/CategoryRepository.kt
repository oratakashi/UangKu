package com.oratakashi.uangku.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.oratakashi.uangku.data.model.CategoryEntity
import io.reactivex.Single

interface CategoryRepository {
    fun add(data: CategoryEntity): Single<Long>
    fun delete(data: CategoryEntity): Single<Int>
    fun getAll(): LiveData<PagingData<CategoryEntity>>
    fun getByType(type: String): LiveData<PagingData<CategoryEntity>>
    fun searchByName(name: String): LiveData<PagingData<CategoryEntity>>
    fun searchByNameAndType(name: String, type: String): LiveData<PagingData<CategoryEntity>>
}