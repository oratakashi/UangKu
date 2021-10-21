package com.oratakashi.uangku.models

enum class SummaryItemColor {
    GREEN,
    RED,
    CYAN,
    BLUE
}

data class SummaryItem(
    val total: Long,
    val category: String,
    val color: SummaryItemColor
)
