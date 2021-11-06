package com.oratakashi.uangku.component.picker.activitytype

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oratakashi.uangku.databinding.FragmentActivityTypePickerBinding
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.bottomsheet.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick


class ActivityTypePickerFragment : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            tvIncome.onClick {
                viewModel.setActivityType(ActivityType.INCOME)
                dismiss()
            }
            tvExpanse.onClick {
                viewModel.setActivityType(ActivityType.EXPANSE)
                dismiss()
            }
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
    private val viewModel: ActivityTypePickerVM by activityViewModels()
}