package com.oratakashi.uangku.ui.menu.settings.category.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.uangku.domain.CategoryUsecase
import com.oratakashi.uangku.domain.model.Category
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.retrofit.transformer.composeSingle
import io.reactivex.disposables.CompositeDisposable

class CreateCategoryViewModel(
    private val usecase: CategoryUsecase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    private val _state: MutableLiveData<Boolean> by liveData()
    val state: LiveData<Boolean> = _state

    fun addCategory(data: Category) {
        usecase.add(data).compose(composeSingle()).subscribe({
            _state.value = true
        }, {
            _state.value = false
        }).let { return@let disposable::add }
    }
}