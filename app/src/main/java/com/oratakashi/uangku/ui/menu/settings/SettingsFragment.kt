package com.oratakashi.uangku.ui.menu.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.FragmentSettingsBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick


class SettingsFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            menuCategory.onClick {
                nav.navigate(SettingsFragmentDirections.actionSettingsFragmentToCategoryFragment())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private val binding: FragmentSettingsBinding by viewBinding()
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
}