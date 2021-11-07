package com.oratakashi.uangku.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import com.oratakashi.uangku.data.CategoryRepository
import com.oratakashi.uangku.domain.model.Category
import io.reactivex.Single

class CategoryInteractor(
    private val repository: CategoryRepository
) : CategoryUsecase {
    override fun add(data: Category): Single<Long> {
        return repository.add(data.toCategoryEntity())
    }

    override fun delete(data: Category): Single<Int> {
        return repository.delete(data.toCategoryEntity())
    }

    override fun getAll(): LiveData<PagingData<Category>> {
        return repository.getAll().map { data -> data.map { it.toCategory() } }
    }

    override fun getByType(type: String): LiveData<PagingData<Category>> {
        return repository.getByType(type).map { data -> data.map { it.toCategory() } }
    }

    override fun searchByName(name: String): LiveData<PagingData<Category>> {
        return repository.searchByName(name).map { data -> data.map { it.toCategory() } }
    }

    override fun searchByNameAndType(name: String, type: String): LiveData<PagingData<Category>> {
        return repository.searchByNameAndType(name, type)
            .map { data -> data.map { it.toCategory() } }
    }
}