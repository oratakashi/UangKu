package com.oratakashi.uangku.ui.menu.settings.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.FragmentCategoryBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding

class CategoryFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            searchBar.setupWithNavController(nav)
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
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
}