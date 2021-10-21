package com.oratakashi.uangku.component.widget

import android.content.Context
import android.util.AttributeSet
import androidx.navigation.NavController
import com.google.android.material.appbar.MaterialToolbar
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.LayoutSearchBarBinding
import com.oratakashi.viewbinding.core.binding.customview.viewBinding
import com.oratakashi.viewbinding.core.tools.onClick

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    MaterialToolbar(context, attrs, defStyleAttr) {

    private val binding: LayoutSearchBarBinding by viewBinding()

    fun hint(text: String){
        with(binding){
            etSearch.hint = text
        }
    }

    fun setupWithNavController(nav: NavController) {
        with(binding){
            ivBack.onClick { nav.navigateUp() }
        }
    }



    init {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.SearchBar, 0, 0)
            hint(styledAttributes.getString(R.styleable.SearchBar_android_hint) ?: "")
        }
        binding.root
    }
}