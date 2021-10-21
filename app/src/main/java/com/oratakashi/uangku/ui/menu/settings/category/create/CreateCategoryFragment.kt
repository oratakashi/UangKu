package com.oratakashi.uangku.ui.menu.settings.category.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oratakashi.uangku.R
import com.oratakashi.uangku.component.picker.activitytype.ActivityTypePickerFragment
import com.oratakashi.uangku.databinding.FragmentCreateCategoryBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick


class CreateCategoryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.setupWithNavController(nav)

            spnType.onClick {
                ActivityTypePickerFragment().show(childFragmentManager, "dialog")
            }
        }
    }

    private fun initObserver() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentCreateCategoryBinding by viewBinding()
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
}