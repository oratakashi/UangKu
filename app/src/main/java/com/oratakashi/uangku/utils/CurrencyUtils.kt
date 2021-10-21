package com.oratakashi.uangku.utils

import java.text.NumberFormat
import java.util.*

fun Double.toCurrency(): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getNumberInstance(localeID)
    return "Rp ${numberFormat.format(this)}"
}

fun Long.toCurrency(): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getNumberInstance(localeID)
    return "Rp ${numberFormat.format(this)}"
}