package com.oratakashi.uangku.ui.menu.transaction.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.uangku.domain.TransactionUsecase
import com.oratakashi.uangku.domain.model.Transaction
import com.oratakashi.viewbinding.core.binding.livedata.liveData
import com.oratakashi.viewbinding.core.tools.retrofit.transformer.composeSingle
import io.reactivex.disposables.CompositeDisposable

class FormTransactionViewModel(
    private val usecase: TransactionUsecase,
    private val disposable: CompositeDisposable
) : ViewModel() {
    private val _state: MutableLiveData<Boolean> by liveData()
    val state: LiveData<Boolean> = _state

    private var _date: String = ""
    val date: String
        get() = _date

    private var _ammount: String = ""
    val ammount: String
        get() = _ammount

    private var _description: String = ""
    val description: String
        get() = _description

    fun addTransaction(data: Transaction) {
        usecase.add(data).compose(composeSingle()).subscribe({
            _state.postValue(true)
        },{
            it.printStackTrace()
            _state.postValue(false)
        }).let { return@let disposable::add }
    }

    fun saveTmpForm(
        date: String,
        ammount: String,
        description: String
    ) {
        _date = date
        _ammount = ammount
        _description = description
    }

    fun clear() {
        _date = ""
        _ammount = ""
        _description = ""
    }
}