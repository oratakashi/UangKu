package com.oratakashi.uangku.ui.menu.settings.category

import android.util.Log
import android.widget.EditText
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jakewharton.rxbinding3.widget.TextViewTextChangeEvent
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.oratakashi.uangku.domain.CategoryUsecase
import com.oratakashi.uangku.domain.model.Category
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.retrofit.transformer.composeObservable
import com.oratakashi.viewbinding.core.tools.retrofit.transformer.composeSingle
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit

class CategoryViewModel(
    private val usecase: CategoryUsecase,
    private val disposable: CompositeDisposable
) : ViewModel() {

    private val filter: MutableLiveData<ActivityType> by liveData(ActivityType.ALL)
    private val search: MutableLiveData<String> by liveData("")

    val data: LiveData<PagingData<Category>> = Transformations.switchMap(search) {
        if (it.isNotEmpty()) {
            Transformations.switchMap(filter) { filters ->
                if (filters != ActivityType.ALL) {
                    usecase.searchByNameAndType(it, filters.value).cachedIn(viewModelScope)
                } else {
                    usecase.searchByName(it).cachedIn(viewModelScope)
                }
            }
        } else {
            Transformations.switchMap(filter) { filters ->
                if (filters != ActivityType.ALL) {
                    usecase.getByType(filters.value).cachedIn(viewModelScope)
                } else {
                    usecase.getAll().cachedIn(viewModelScope)
                }
            }
        }
    }.apply {
        filter.value = ActivityType.ALL
        search.value = ""
    }

    private val _delete: MutableLiveData<Boolean> by liveData()
    val delete: LiveData<Boolean> = _delete

    fun delete(data: Category) {
        usecase.delete(data).compose(composeSingle()).subscribe({
            _delete.value = true
        }, {
            _delete.value = false
        }).let { return@let disposable::add }
    }

    fun setupSearch(editText: EditText) {
        editText.textChangeEvents()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .compose(composeObservable())
            .subscribeWith(object : DisposableObserver<TextViewTextChangeEvent>(){
                override fun onNext(t: TextViewTextChangeEvent) {
                    val keyword = t.text.trim().toString()
                    if(keyword.isNotBlank() && keyword.isNotEmpty() && keyword.length >= 3){
                        search.value = keyword
                    }else {
                        search.value = ""
                    }
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            }).let { return@let disposable::add }
    }
}