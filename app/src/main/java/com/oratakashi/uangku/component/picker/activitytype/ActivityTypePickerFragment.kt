package com.oratakashi.uangku.component.picker.activitytype

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.FragmentActivityTypePickerBinding
import com.oratakashi.viewbinding.core.binding.bottomsheet.viewBinding


class ActivityTypePickerFragment : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private val binding: FragmentActivityTypePickerBinding by viewBinding()
}