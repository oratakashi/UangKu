package com.oratakashi.uangku.ui.menu.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.oratakashi.uangku.R
import com.oratakashi.uangku.component.widget.TransactionList
import com.oratakashi.uangku.databinding.FragmentDashboardBinding
import com.oratakashi.uangku.models.TransactionCardColor
import com.oratakashi.uangku.models.TransactionCardType
import com.oratakashi.uangku.models.TransactionData
import com.oratakashi.uangku.utils.enums.ActivityType
import com.oratakashi.viewbinding.core.binding.fragment.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick
import com.oratakashi.viewbinding.core.tools.toast

class DashboardFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fabIncome.onClick {
                nav.navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToCreateTransactionFragment(
                        ActivityType.INCOME.value
                    )
                )
            }
            fabExpanse.onClick {
                nav.navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToCreateTransactionFragment(
                        ActivityType.EXPANSE.value
                    )
                )
            }
            summaryListView.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    TransactionList(
                        cardType = TransactionCardType.DETAIL,
                        onItemClick = {
                            Toast.makeText(requireContext(), it.category, Toast.LENGTH_SHORT).show()
                        },
                        transactionItems = emptyList()
//                        transactionItems = listOf(
//                            TransactionData(
//                                total = 10000000,
//                                category = "Makanan",
//                                color = TransactionCardColor.GREEN,
//                                date = "17 Oktober 2021",
//                                description = "KFC"
//                            ),
//                            TransactionData(
//                                total = 500000,
//                                category = "Reksa dana",
//                                color = TransactionCardColor.RED,
//                                date = "18 Oktober 2021",
//                                description = "Sucor"
//                            ),
//                            TransactionData(
//                                total = 250000,
//                                category = "Kebutuhan Harian",
//                                color = TransactionCardColor.CYAN,
//                                date = "19 Oktober 2021",
//                                description = "Skincare"
//                            ),
//                            TransactionData(
//                                total = 1000000,
//                                category = "Diamond Mobile Legend",
//                                color = TransactionCardColor.BLUE,
//                                date = "20 Oktober 2021",
//                                description = "Skin legend badang"
//                            ),
//                        )
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
    private val nav: NavController by lazy { requireActivity().findNavController(R.id.nav_host_fragment_main) }
}