package com.oratakashi.uangku.utils

import android.annotation.SuppressLint
import com.oratakashi.uangku.utils.enums.ConverterDate
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.convertDate(input: ConverterDate, output: ConverterDate): String {
    val parser = SimpleDateFormat(input.formatter)
    val formatter = SimpleDateFormat(output.formatter)
    return try {
        formatter.format(parser.parse(this)!!)
    } catch (e: Exception) {
        print(e)
        e.printStackTrace()
        this
    }
}

fun String.stringToDate(output: ConverterDate): Date {
    return try {
        SimpleDateFormat(output.formatter, Locale.getDefault()).parse(this) ?: Date()
    } catch (e: Exception) {
        Date()
    }
}

fun Date.toStringDate(output: ConverterDate): String {
    return SimpleDateFormat(output.formatter, Locale.getDefault()).format(this)
}

fun Date.dateToCalendar(): Calendar {
    return Calendar.getInstance().also {
        it.time = this
    }
}