package com.oratakashi.uangku.component.picker.deletedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.FragmentDeleteDialogBinding
import com.oratakashi.viewbinding.core.binding.bottomsheet.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick


class DeleteDialogFragment(
    private val message: String,
    private val onDelete: () -> Unit
) : BottomSheetDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
//            btnCancel.onClick { dismiss() }
//            btnDelete.onClick {
//                onDelete.invoke()
//                dismiss()
//            }

            tvMessage.text = String.format(
                getString(R.string.placeholder_delete_message),
                message
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentDeleteDialogBinding by viewBinding()
}