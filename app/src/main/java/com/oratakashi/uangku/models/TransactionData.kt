package com.oratakashi.uangku.models


data class TransactionData(
    val total: Long,
    val category: String,
    val description: String,
    val color: TransactionCardColor,
    val date: String
)


sealed class TransactionCardType {
    object SIMPLE: TransactionCardType()
    object DETAIL: TransactionCardType()
}

enum class TransactionCardColor {
    GREEN,
    RED,
    CYAN,
    BLUE
}
