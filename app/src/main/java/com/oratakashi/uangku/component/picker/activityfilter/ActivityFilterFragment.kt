package com.oratakashi.uangku.component.picker.activityfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oratakashi.uangku.databinding.FragmentActivityFilterBinding
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class ActivityFilterFragment : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            tvAll.onClick {
                viewModel.setActivityType(ActivityType.ALL)
                dismiss()
            }
            tvExpanse.onClick {
                viewModel.setActivityType(ActivityType.EXPANSE)
                dismiss()
            }
            tvIncome.onClick {
                viewModel.setActivityType(ActivityType.INCOME)
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentActivityFilterBinding by viewBinding()
    private val viewModel: ActivityFilterVM by activityViewModels()
}