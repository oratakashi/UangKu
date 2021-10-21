package com.oratakashi.uangku.ui.menu.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.oratakashi.uangku.R
import com.oratakashi.uangku.component.SummaryList
import com.oratakashi.uangku.databinding.FragmentDashboardBinding
import com.oratakashi.uangku.models.SummaryItem
import com.oratakashi.uangku.models.SummaryItemColor
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast

class DashboardFragment : Fragment() {

    @ExperimentalMaterialApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fabIncome.onClick { toast("income") }
            fabExpanse.onClick { toast("expanse") }
            summaryListView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    SummaryList(
                        summaryItems = listOf(
                            SummaryItem(
                                10000000,
                                "Makanan",
                                SummaryItemColor.GREEN
                            ),
                            SummaryItem(
                                500000,
                                "Reksa dana",
                                SummaryItemColor.RED
                            ),
                            SummaryItem(
                                250000,
                                "Kebutuhan Harian",
                                SummaryItemColor.CYAN
                            ),
                            SummaryItem(
                                1000000,
                                "Diamond Mobile Legend",
                                SummaryItemColor.BLUE
                            ),
                        )
                    )
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private val binding: FragmentDashboardBinding by viewBinding()
}