package com.oratakashi.uangku.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oratakashi.uangku.R
import com.oratakashi.uangku.models.SummaryItem
import com.oratakashi.uangku.models.SummaryItemColor
import com.oratakashi.uangku.utils.montserratFont
import com.oratakashi.uangku.utils.toCurrency

@ExperimentalMaterialApi
@Composable
fun SummaryList(
    summaryItems: List<SummaryItem>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.label_summary),
            fontFamily = montserratFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 17.dp, horizontal = 14.dp)
        )
        LazyColumn(
            modifier = Modifier.padding(horizontal = 14.dp),
            content = {
                items(summaryItems) { item ->
                    SummaryItemComponent(
                        item = item,
                        onItemClick = {}
                    )
                }
            })
    }
}

@ExperimentalMaterialApi
@Composable
fun SummaryItemComponent(
    item: SummaryItem,
    onItemClick: (SummaryItem) -> Unit = {}
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
                    .height(60.dp)
                    .background(handleSummaryColor(color = item.color)),
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 10.dp)
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
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
        }
    }
}

@Composable
private fun handleSummaryColor(color: SummaryItemColor): Color {
    return when (color) {
        SummaryItemColor.GREEN -> colorResource(id = R.color.green50)
        SummaryItemColor.RED -> colorResource(id = R.color.rose50)
        SummaryItemColor.CYAN -> colorResource(id = R.color.cyan)
        SummaryItemColor.BLUE -> colorResource(id = R.color.darkBlue)
    }
}