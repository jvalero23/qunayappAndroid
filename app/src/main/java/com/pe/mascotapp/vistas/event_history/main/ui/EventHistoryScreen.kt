package com.pe.mascotapp.vistas.event_history.main.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.pe.mascotapp.R
import com.pe.mascotapp.caprasimoFontFamily
import com.pe.mascotapp.colorMediumBlue
import com.pe.mascotapp.vistas.event_history.main.components.EventHistoryItem
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme
import com.pe.mascotapp.workSansFontFamily

@Composable
fun EventHistoryScreen(
    modifier: Modifier = Modifier,
    onClickSeeFilter: () -> Unit,
    onClickSeeAll: (id: String) -> Unit,
    onAddHistory: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onAddHistory,
            modifier = Modifier
                .zIndex(2f)
                .padding(bottom = 12.dp),
            contentPadding = PaddingValues(vertical = 17.dp, horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorMediumBlue,
                contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text(
                text = "Agregar historia",
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 24.2.sp
            )
        }
        Box(
            modifier = modifier
                .fillMaxSize()
                .zIndex(0f)
        ) {
            ScreenContent(
                modifier = Modifier.zIndex(1f),
                onClickSeeFilter = onClickSeeFilter,
                onClickSeeAll = onClickSeeAll
            )
            Image(
                painter = painterResource(R.drawable.background_pets_griss),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .zIndex(0f)
            )
        }
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    onClickSeeFilter: () -> Unit,
    onClickSeeAll: (id: String) -> Unit
) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Historial",
                    fontFamily = caprasimoFontFamily,
                    color = Color(0xFF203E6C),
                    fontSize = 24.sp,
                    lineHeight = 27.53.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Apunta eventos importantes y mantente al tanto de la salud de tu mascota",
                    fontFamily = workSansFontFamily,
                    color = Color(0xFF767676),
                    fontSize = 14.sp,
                    lineHeight = 16.42.sp
                )
            }
            TextButton(
                modifier = Modifier,
                onClick = onClickSeeFilter
            ) {
                Icon(
                    painter = painterResource(R.drawable.filtros),
                    tint = Color(0xFF2A6BAF),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Filtrar",
                    color = Color(0xFF2A6BAF),
                    fontSize = 14.sp,
                )
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        EventList(
            data = sampleDataEvent,
            onClickSeeAll = onClickSeeAll
        )
    }
}

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    data: List<EventHistoryData>,
    onClickSeeAll: (id: String) -> Unit
) {
    val groupedItem = data.groupBy { it.date }
    LazyColumn(modifier = modifier) {
        groupedItem.forEach { (date, items) ->
            item {
                Text(
                    text = date,
                    fontFamily = workSansFontFamily,
                    fontWeight = FontWeight.W500,
                    color = colorMediumBlue,
                    fontSize = 14.sp,
                    lineHeight = 16.42.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            item { HorizontalDivider(color = colorMediumBlue) }
            items(items = items) { item ->
                Spacer(modifier = Modifier.height(12.dp))
                EventHistoryItem(
                    startTime = item.startTime,
                    endTime = item.endTime,
                    imageUri = item.img,
                    showSeparator = items.indexOf(item) != items.size - 1,
                    onClickSeeAll = {
                        onClickSeeAll(item.id)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun EventHistoryScreenPreview() {
    MascotappTheme {
        EventHistoryScreen(
            onClickSeeFilter = {},
            onClickSeeAll = {},
            onAddHistory = {}
        )
    }
}

