package com.oratakashi.uangku.ui.menu.settings.category.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.oratakashi.uangku.R
import com.oratakashi.uangku.component.picker.activitytype.ActivityTypePickerFragment
import com.oratakashi.uangku.component.picker.activitytype.ActivityTypePickerVM
import com.oratakashi.uangku.databinding.FragmentCreateCategoryBinding
import com.oratakashi.uangku.domain.model.Category
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.uangku.utils.hideSoftKeyboard
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class CreateCategoryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initObserver()

//            toolbar.setupWithNavController(nav)

//            etType.editText?.setText(args.type)

//            spnType.onClick {
//                ActivityTypePickerFragment().show(childFragmentManager, "dialog")
//            }
//            etType.setEndIconOnClickListener {
//                ActivityTypePickerFragment().show(childFragmentManager, "dialog")
//            }

//            btnSave.onClick {
//                btnSave.hideSoftKeyboard()
//                when {
//                    etName.editText?.text.toString().isEmpty() -> {
//                        etName.error = String.format(
//                            getString(R.string.placeholder_error_empty),
//                            "Nama Kategori"
//                        )
//                    }
//                    etType.editText?.text.toString().isEmpty() -> {
//                        etType.error = String.format(
//                            getString(R.string.placeholder_error_empty),
//                            "Jenis"
//                        )
//                    }
//                    else -> {
//                        viewModel.addCategory(
//                            Category(
//                                etName.editText?.text.toString(),
//                                etType.editText?.text.toString()
//                            )
//                        )
//                    }
//                }
//            }
        }
    }

    private fun initObserver() {
        with(binding) {
//            pickerVM.activityType.observe(viewLifecycleOwner) {
//                when (it) {
//                    ActivityType.EXPANSE -> {
//                        etType.editText?.setText(ActivityType.EXPANSE.value)
//                    }
//                    ActivityType.INCOME -> {
//                        etType.editText?.setText(ActivityType.INCOME.value)
//                    }
//                    ActivityType.DEFAULT -> {
//                        etType.editText?.setText(args.type)
//                    }
//                    else -> throw IllegalArgumentException()
//                }
//            }
            viewModel.state.observe(viewLifecycleOwner) {
                if(it) {
                    toast("Berhasil Menambah Kategori!")
                    nav.navigateUp()
                } else {
                    toast("Gagal Menambah Kategori!")
                }
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

    override fun onDetach() {
        pickerVM.clear()
        super.onDetach()
    }

    private val binding: FragmentCreateCategoryBinding by viewBinding()
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
    private val args: CreateCategoryFragmentArgs by navArgs()

    private val pickerVM: ActivityTypePickerVM by activityViewModels()
    private val viewModel: CreateCategoryViewModel by viewModel()
}