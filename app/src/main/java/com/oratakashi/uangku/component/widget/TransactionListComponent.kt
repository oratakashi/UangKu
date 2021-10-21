package com.oratakashi.uangku.component.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oratakashi.uangku.R
import com.oratakashi.uangku.models.TransactionCardColor
import com.oratakashi.uangku.models.TransactionCardType
import com.oratakashi.uangku.models.TransactionData
import com.oratakashi.uangku.utils.montserratFont
import com.oratakashi.uangku.utils.toCurrency

@Composable
fun TransactionList(
    cardType: TransactionCardType,
    transactionItems: List<TransactionData>,
    onItemClick: (TransactionData) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .fillMaxWidth(),
        content = {
            items(transactionItems) { item ->
                TransactionCard(
                    item = item,
                    onItemClick = onItemClick,
                    cardType = cardType
                )
            }
        })
}

@Composable
fun TransactionCard(
    cardType: TransactionCardType,
    item: TransactionData,
    onItemClick: (TransactionData) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
        ) {
            Spacer(
                modifier = Modifier
                    .width(16.dp)
                    .background(handleTransactionCardColor(color = item.color))
                    .let {
                        if (cardType is TransactionCardType.SIMPLE) {
                            it.height(60.dp)
                        } else {
                            it.height(85.dp)
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 10.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                when (cardType) {
                    TransactionCardType.DETAIL -> {
                        DetailedTransactionCard(item = item)
                    }
                    TransactionCardType.SIMPLE -> {
                        SimpleTransactionCard(item = item)
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailedTransactionCard(
    item: TransactionData
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = item.total.toCurrency(),
            fontFamily = montserratFont,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        CategoryCard(category = item.category, cardColor = item.color)
    }

    Text(
        text = item.description,
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = colorResource(
            id = R.color.black4
        )
    )
    Text(
        text = item.date,
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = colorResource(
            id = R.color.black4
        )
    )
}

@Composable
private fun SimpleTransactionCard(
    item: TransactionData
) {
    Text(
        text = item.total.toCurrency(),
        fontFamily = montserratFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    )
    Text(
        text = item.category,
        fontFamily = montserratFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = colorResource(
            id = R.color.black4
        )
    )
}

@Composable
private fun RowScope.CategoryCard(
    category: String,
    cardColor: TransactionCardColor
) {
    val mCardColor = handleCategoryCardColor(color = cardColor)
    Surface(
        color = mCardColor,
        modifier = Modifier.align(Alignment.CenterVertically),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = category,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(vertical = 3.dp, horizontal = 8.dp),
            fontSize = 10.sp,
        )
    }
}

@Composable
private fun handleTransactionCardColor(color: TransactionCardColor): Color {
    return when (color) {
        TransactionCardColor.GREEN -> colorResource(id = R.color.green50)
        TransactionCardColor.RED -> colorResource(id = R.color.rose50)
        TransactionCardColor.CYAN -> colorResource(id = R.color.cyan)
        TransactionCardColor.BLUE -> colorResource(id = R.color.darkBlue)
    }
}

@Composable
private fun handleCategoryCardColor(color: TransactionCardColor): Color {
    return when (color) {
        TransactionCardColor.GREEN -> colorResource(id = R.color.green50).copy(alpha = 0.3f)
        TransactionCardColor.RED -> colorResource(id = R.color.rose50).copy(alpha = 0.3f)
        TransactionCardColor.CYAN -> colorResource(id = R.color.cyan).copy(alpha = 0.3f)
        TransactionCardColor.BLUE -> colorResource(id = R.color.darkBlue).copy(alpha = 0.3f)
    }
}
