package com.oratakashi.uangku.component.picker.activityfilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.livedata.liveData

class ActivityFilterVM: ViewModel() {
    private val _activityType : MutableLiveData<ActivityType> by liveData()
    val activityType: LiveData<ActivityType> = _activityType

    fun setActivityType(activityType: ActivityType) {
        _activityType.postValue(activityType)
    }
}