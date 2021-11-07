package com.oratakashi.uangku.ui.menu.settings.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oratakashi.uangku.R
import com.oratakashi.uangku.component.picker.deletedialog.DeleteDialogFragment
import com.oratakashi.uangku.databinding.FragmentCategoryBinding
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment() {

    private val adapter: CategoryAdapter by lazy {
        CategoryAdapter {
            DeleteDialogFragment("Kategori") {
                viewModel.delete(it)
            }.show(childFragmentManager, "dialog")
        }
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

            rvCategory.adapter = adapter

            tvFilter.text = ActivityType.ALL.value

            viewModel.setupSearch(searchBar.editText)
        }
    }

    private fun initObserver() {
        with(binding) {
            viewModel.data.observe(viewLifecycleOwner) { adapter.submitData(lifecycle, it) }
        }
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
    private val disposable: CompositeDisposable by inject()
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
}