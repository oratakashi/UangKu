package com.oratakashi.uangku.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.oratakashi.uangku.domain.model.Category
import io.reactivex.Single

interface CategoryUsecase {
    fun add(data: Category): Single<Long>
    fun delete(data: Category): Single<Int>
    fun getAll(): LiveData<PagingData<Category>>
    fun getByType(type: String): LiveData<PagingData<Category>>
    fun searchByName(name: String): LiveData<PagingData<Category>>
    fun searchByNameAndType(name: String, type: String): LiveData<PagingData<Category>>
}