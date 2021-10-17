package com.oratakashi.uangku.ui.menu.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.FragmentSettingsBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding


class SettingsFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentSettingsBinding by viewBinding()
}