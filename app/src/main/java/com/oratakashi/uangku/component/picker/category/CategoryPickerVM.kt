package com.oratakashi.uangku.component.picker.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.uangku.domain.model.Category

class CategoryPickerVM : ViewModel() {
    private val _category = MutableLiveData<Category?>().apply { value = null }
    val category: LiveData<Category?> = _category

    fun setCategory(category: Category) {
        _category.postValue(category)
    }

    fun clear() {
        _category.postValue(null)
    }
}