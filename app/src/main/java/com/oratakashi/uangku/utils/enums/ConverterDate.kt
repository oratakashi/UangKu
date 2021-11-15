package com.oratakashi.uangku.utils.enums

enum class ConverterDate(val formatter: String) {
    TIME_ONLY("HH:mm"),
    FULL_DATE("dd MMMM yyyy"),
    SHORT_DATE("dd MMM yyyy"),
    SIMPLE_DATE("dd/MM/yyyy"),
    SQL_DATE("yyyy-MM-dd"),
    SIMPLE_DAY("EEE"),
    SIMPLE_MONTH("MMM"),
    SIMPLE_CHART("dd MMM")
}