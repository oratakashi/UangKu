package com.oratakashi.uangku.component.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.CustomRedButtonBinding
import com.oratakashi.viewbinding.core.binding.customview.viewBinding

class RedButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding: CustomRedButtonBinding by viewBinding()

    fun text(text: String?) {
        with(binding) {
            if (text != null) {
                btnText.text = text
            }
        }
    }

    init {
        attrs?.let {
            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.RedButton, 0, 0)
            val text = styledAttributes.getString(R.styleable.RedButton_android_text)
            text(text)
        }
    }
}