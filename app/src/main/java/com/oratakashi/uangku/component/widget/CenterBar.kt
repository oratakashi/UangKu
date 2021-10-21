package com.oratakashi.uangku.component.widget

import android.content.Context
import android.util.AttributeSet
import androidx.navigation.NavController
import com.google.android.material.appbar.MaterialToolbar
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.LayoutCenterbarBinding
import com.oratakashi.viewbinding.core.binding.customview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class CenterBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    MaterialToolbar(context, attrs, defStyleAttr) {

    private val binding: LayoutCenterbarBinding by viewBinding()

    fun title(text: String){
        with(binding){
            tvTitle.text = text
        }
    }

    fun setupWithNavController(nav: NavController) {
        with(binding){
            ivBack.onClick { nav.navigateUp() }
        }
    }

    init {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.CenterBar, 0, 0)
            title(styledAttributes.getString(R.styleable.CenterBar_android_text) ?: "")
        }
        binding.root
    }
}