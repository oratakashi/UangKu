package com.oratakashi.uangku.ui.menu.transaction.form

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
import com.oratakashi.uangku.component.picker.category.CategoryPickerVM
import com.oratakashi.uangku.databinding.FragmentFormTransactionBinding
import com.oratakashi.uangku.domain.model.Category
import com.oratakashi.uangku.domain.model.Transaction
import com.oratakashi.uangku.utils.*
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.uangku.utils.enums.ConverterDate
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class FormTransactionFragment : Fragment() {

    private var type: ActivityType = ActivityType.ALL
    private var category: Category? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initObserver()

            when(args.type) {
                ActivityType.INCOME.value   -> {
                    type = ActivityType.INCOME
                    rbIncome.isChecked = true
                }
                ActivityType.EXPANSE.value   -> {
                    type = ActivityType.EXPANSE
                    rbExpanse.isChecked = true
                }
            }

            toolbar.setupWithNavController(nav)
            etCalendar.apply {
                setEndIconOnClickListener { showDatePickerActivity(this, requireActivity(), false) }
                editText?.onClick { showDatePickerActivity(this, requireActivity(), false) }
                editText?.setText(Date().toStringDate(ConverterDate.FULL_DATE))
            }
            etCategory.apply {
                setEndIconOnClickListener {
                    hideSoftKeyboard()
                    viewModel.saveTmpForm(
                        etCalendar.editText?.text.toString(),
                        etAmmount.editText?.text.toString(),
                        etNote.editText?.text.toString()
                    )
                    nav.navigate(
                        FormTransactionFragmentDirections.actionCreateTransactionFragmentToCategoryFragment(
                            isPicker = true,
                            type = type.value
                        )
                    )
                }
                editText?.setOnClickListener {
                    hideSoftKeyboard()
                    viewModel.saveTmpForm(
                        etCalendar.editText?.text.toString(),
                        etAmmount.editText?.text.toString(),
                        etNote.editText?.text.toString()
                    )
                    nav.navigate(
                        FormTransactionFragmentDirections.actionCreateTransactionFragmentToCategoryFragment(
                            isPicker = true,
                            type = type.value
                        )
                    )
                }
            }
            etAmmount.addCurrencyTextWatcher()

            rgType.setOnCheckedChangeListener { _, checkedId ->
                etCategory.editText?.setText("")
                categoryPicker.clear()
                when(checkedId){
                    R.id.rbIncome   -> {
                        type = ActivityType.INCOME
                    }
                    R.id.rbExpanse  -> {
                        type = ActivityType.EXPANSE
                    }
                }
            }

            btnSave.setOnClickListener {
                it.hideSoftKeyboard()
                when {
                    etCalendar.editText?.text.toString().isEmpty() -> {
                        etCalendar.error = String.format(
                            getString(R.string.placeholder_error_empty),
                            "Tanggal"
                        )
                    }
                    etAmmount.editText?.text.toString().isEmpty() -> {
                        etAmmount.error = String.format(
                            getString(R.string.placeholder_error_empty),
                            "Nominal"
                        )
                    }
                    etNote.editText?.text.toString().isEmpty() -> {
                        etNote.error = String.format(
                            getString(R.string.placeholder_error_empty),
                            "Keterangan"
                        )
                    }
                    etCategory.editText?.text.toString().isEmpty() && category != null -> {
                        etCategory.error = String.format(
                            getString(R.string.placeholder_error_empty),
                            "Kategori"
                        )
                    }
                    else -> {
                        viewModel.addTransaction(
                            Transaction(
                                etCalendar.editText?.text.toString().convertDate(
                                    ConverterDate.FULL_DATE,
                                    ConverterDate.SQL_DATE
                                ),
                                etAmmount.editText?.text.toString().replace("." ,"").toLong(),
                                etNote.editText?.text.toString(),
                                type.value,
                                category?.id.orEmpty()
                            )
                        )
                    }
                }
            }
        }
    }

    private fun initObserver() {
        with(binding) {
            categoryPicker.category.observe(viewLifecycleOwner) {
                it?.let {
                    category = it
                    etCategory.editText?.setText(it.name)
                }
            }
            viewModel.state.observe(viewLifecycleOwner) {
                if(it) {
                    toast("Berhasil Menambah Transaksi!")
                    nav.navigateUp()
                } else {
                    toast("Gagal Menambah Transaksi!")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(binding) {
            if(viewModel.date.isNotEmpty()) {
                etCalendar.editText?.setText(viewModel.date)
            }
            etAmmount.editText?.setText(viewModel.ammount)
            etNote.editText?.setText(viewModel.description)
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
        super.onDetach()
        categoryPicker.clear()
        viewModel.clear()
    }

    private val viewModel: FormTransactionViewModel by viewModel()
    private val categoryPicker: CategoryPickerVM by activityViewModels()
    private val binding: FragmentFormTransactionBinding by viewBinding()
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
    private val args: FormTransactionFragmentArgs by navArgs()
}