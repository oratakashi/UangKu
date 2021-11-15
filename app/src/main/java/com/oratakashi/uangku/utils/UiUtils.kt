package com.oratakashi.uangku.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import com.oratakashi.uangku.R
import com.oratakashi.uangku.utils.enums.ConverterDate
import java.text.NumberFormat
import java.util.*

/**
 * Method to show keyboard from selected view
 */
fun View.showKeyboard() {
    if (this.requestFocus()) {
        (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

/**
 * Method to hide keyboard from selected view
 */
fun View.hideSoftKeyboard() {
    (this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(this.windowToken, 0)
}

fun setListenerDropdown(view: AutoCompleteTextView, childs: List<AutoCompleteTextView>) {
    view.setOnItemClickListener { _, _, _, _ ->
        childs.forEach {
            it.setText("")
        }
    }
}

fun TextInputLayout.addCurrencyTextWatcher() {
    val et = this.editText
    val that = this

    val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            et?.let {
                et.removeTextChangedListener(this)
                if (s.toString() != "") {
                    if (s.toString().substring(0, 1) == "0") {
                        if (s.toString().length > 1) {
                            if (s.toString().substring(0, 1) == "0" && s.toString()
                                    .substring(1, 2) !== "0"
                            ) {
                                et.setText(
                                    formatCurrency(
                                        StringBuilder(s.toString()).deleteCharAt(
                                            0
                                        ).toString().toDouble()
                                    )
                                )
                            } else if (s.toString().substring(0, 1) != "0") {
                                et.setText(formatCurrency(s.toString().toDouble()))
                            } else {
                                et.setText("0")
                                that.placeholderText = ""
                            }
                        } else {
                            et.setText(formatCurrency(s.toString().toDouble()))
                        }
                    } else {
                        val cleanString = s.toString().replace(".", "")
                        et.setText(formatCurrency(cleanString.toDouble()))
                    }
                } else {
                    et.setText("0")
                    that.placeholderText = ""
                }

                et.setSelection(et.length())
                et.addTextChangedListener(this)
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    et?.addTextChangedListener(watcher)
}

fun formatCurrency(number: Double): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getNumberInstance(localeID)
    return numberFormat.format(number)
}

inline fun <T : View> T.afterMeasured(crossinline action: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                action()
            }
        }
    })
}

@SuppressLint("SetTextI18n")
fun showDatePicker(
    view: TextInputLayout,
    context: FragmentActivity,
    tag: String = "Datepicker",
) {
    val builder = MaterialDatePicker.Builder.datePicker()
    val datePicker = builder.build()
    datePicker.addOnPositiveButtonClickListener {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.time = Date(it)
        view.editText?.setText(
            "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                calendar.get(
                    Calendar.YEAR
                )
            }".convertDate(
                ConverterDate.SIMPLE_DATE,
                ConverterDate.FULL_DATE
            )
        )
    }
    datePicker.show(context.supportFragmentManager, tag)
}

@SuppressLint("SetTextI18n")
fun showDatePickerActivity(
    view: TextInputLayout,
    context: FragmentActivity,
    withLimit: Boolean = true,
    tag: String = "Datepicker",
) {
    val constraintsBuilder =
        CalendarConstraints.Builder().apply {
            if (withLimit) setValidator(DateValidatorPointBackward.now())
        }

    val builder =
        MaterialDatePicker.Builder.datePicker().setCalendarConstraints(constraintsBuilder.build())
    val datePicker = builder.build()
    datePicker.addOnPositiveButtonClickListener {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.time = Date(it)
        view.editText?.setText(
            "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${
                calendar.get(
                    Calendar.YEAR
                )
            }".convertDate(
                ConverterDate.SIMPLE_DATE,
                ConverterDate.FULL_DATE
            )
        )
    }
    datePicker.show(context.supportFragmentManager, tag)
}

fun setDropdownAdapter(view: AutoCompleteTextView, context: Context, list: Array<String>) {
    val adapter = ArrayAdapter(
        context, R.layout.item_dropdown_list,
        list
    )
    view.setAdapter(adapter)
    view.threshold = 100
}