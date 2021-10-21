package com.oratakashi.uangku.component.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.oratakashi.uangku.R
import com.oratakashi.uangku.databinding.CustomPrimaryButtonBinding
import com.oratakashi.uangku.utils.enums.Type
import com.oratakashi.viewbinding.core.binding.customview.viewBinding
import com.oratakashi.viewbinding.core.tools.disable
import com.oratakashi.viewbinding.core.tools.enable
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.visible

//
// Created by oratakashi on 28/09/21.
//
class PrimaryButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CustomPrimaryButtonBinding by viewBinding()

    var option = Type.ENABLED.value
    var allCaps = true

    init {
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.PrimaryButton, 0, 0)
            val text = styledAttributes.getString(R.styleable.PrimaryButton_android_text)
            allCaps = styledAttributes.getBoolean(R.styleable.PrimaryButton_allCaps, true)
            option = styledAttributes.getInt(R.styleable.PrimaryButton_type, Type.ENABLED.value)
            allCaps(allCaps)
            text(text)
            type(option)
        }
    }

    fun allCaps(isAllCaps: Boolean) {
        with(binding) {
            btnText.isAllCaps = isAllCaps
        }
    }

    fun text(text: String?) {
        with(binding) {
            if (text != null) {
                btnText.text = text
            }
        }
    }

    fun onClick(listenerBtn: () -> Unit) {
        with(binding) {
            when (option) {
                Type.ENABLED.value -> {
                    btnPrimary.setOnClickListener { listenerBtn.invoke() }
                }
            }
        }
    }

    fun type(option: Int?) {
        with(binding) {
            when (option) {
                Type.DISABLED.value -> {
                    btnPrimary.background =
                        ContextCompat.getDrawable(context, R.drawable.bg_button_disable)
                    if (Build.VERSION.SDK_INT < 23) {
                        btnText.setTextAppearance(context, R.style.Montserrat_Black1_14sp)
                    } else {
                        btnText.setTextAppearance(R.style.Montserrat_Black1_14sp)
                    }
                    btnText.visible()
                    pbButton.gone()
                    btnPrimary.disable()
                }
                Type.LOADING.value -> {
                    btnPrimary.background =
                        ContextCompat.getDrawable(context, R.drawable.bg_button_primary)
                    btnText.gone()
                    pbButton.visible()
                    btnPrimary.disable()
                }
                else -> {
                    btnPrimary.background =
                        ContextCompat.getDrawable(context, R.drawable.bg_button_primary)
                    if (Build.VERSION.SDK_INT < 23) {
                        btnText.setTextAppearance(context, R.style.Montserrat_White_14sp)
                    } else {
                        btnText.setTextAppearance(R.style.Montserrat_White_14sp)
                    }
                    btnText.visible()
                    pbButton.gone()
                    btnPrimary.enable()
                }
            }
        }
    }
}