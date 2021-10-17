package com.oratakashi.uangku.ui.menu.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.FragmentDashboardBinding
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast


class DashboardFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            fabIncome.onClick { toast("income") }
            fabExpanse.onClick { toast("expanse") }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private val binding: FragmentDashboardBinding by viewBinding()
}