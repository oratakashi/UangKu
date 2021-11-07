package com.oratakashi.uangku.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

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