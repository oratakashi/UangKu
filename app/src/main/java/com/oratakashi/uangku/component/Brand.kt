package com.oratakashi.uangku.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.oratakashi.uangku.databinding.LayoutBrandBinding
import com.oratakashi.viewbinding.core.binding.customview.viewBinding

class Brand @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: LayoutBrandBinding by viewBinding()

    init {
        binding.root
    }
}