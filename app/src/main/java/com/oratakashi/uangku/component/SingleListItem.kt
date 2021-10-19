package com.oratakashi.uangku.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.LayoutSingleListItemBinding
import com.oratakashi.viewbinding.core.binding.customview.viewBinding

class SingleListItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutSingleListItemBinding by viewBinding()

    fun text(text: String?) {
        with(binding){
            if(text != null){
                tvTitle.text = text
            }
        }
    }

    init {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.SingleListItem, 0, 0)
            text(styledAttributes.getString(R.styleable.SingleListItem_android_text))
        }
    }
}