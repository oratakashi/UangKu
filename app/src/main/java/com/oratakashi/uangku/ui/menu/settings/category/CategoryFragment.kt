package com.oratakashi.uangku.ui.menu.settings.category

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
import com.oratakashi.uangku.component.picker.activityfilter.ActivityFilterFragment
import com.oratakashi.uangku.component.picker.activityfilter.ActivityFilterVM
import com.oratakashi.uangku.component.picker.category.CategoryPickerVM
import com.oratakashi.uangku.component.picker.deletedialog.DeleteDialogFragment
import com.oratakashi.uangku.databinding.FragmentCategoryBinding
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {

    private val adapter: CategoryAdapter by lazy {
        CategoryAdapter(
            onClick = {
                if (args.isPicker) {
                    pickerVM.setCategory(it)
                    nav.navigateUp()
                }
            },
            onDelete = {
                DeleteDialogFragment("Kategori") {
                    viewModel.delete(it)
                }.show(childFragmentManager, "dialog")
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initObserver()

            searchBar.setupWithNavController(nav)
            fabIncome.onClick {
                nav.navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToCreateCategoryFragment(
                        ActivityType.INCOME.value
                    )
                )
            }
            fabExpanse.onClick {
                nav.navigate(
                    CategoryFragmentDirections.actionCategoryFragmentToCreateCategoryFragment(
                        ActivityType.EXPANSE.value
                    )
                )
            }
            cvFilter.onClick {
                if (!args.isPicker) ActivityFilterFragment().show(childFragmentManager, "dialog")
            }

            rvCategory.adapter = adapter

            tvFilter.text = ActivityType.ALL.value

            viewModel.setupSearch(searchBar.editText)

            if (args.isPicker) {
                args.type?.let {
                    tvFilter.text = it
                    when (it) {
                        ActivityType.INCOME.value -> viewModel.filter(ActivityType.INCOME)
                        ActivityType.EXPANSE.value -> viewModel.filter(ActivityType.EXPANSE)
                        ActivityType.ALL.value -> viewModel.filter(ActivityType.ALL)
                    }
                }
            }
        }
    }

    private fun initObserver() {
        with(binding) {
            viewModel.data.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
            filterVM.activityType.observe(viewLifecycleOwner) {
                it?.let {
                    tvFilter.text = it.value
                    viewModel.filter(it)
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        filterVM.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentCategoryBinding by viewBinding()
    private val viewModel: CategoryViewModel by viewModel()
    private val filterVM: ActivityFilterVM by activityViewModels()
    private val pickerVM: CategoryPickerVM by activityViewModels()
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
    private val args: CategoryFragmentArgs by navArgs()
}