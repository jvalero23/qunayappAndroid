package com.pe.mascotapp.vistas.event_history.filter.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pe.mascotapp.R
import com.pe.mascotapp.caprasimoFontFamily
import com.pe.mascotapp.colorDisabled
import com.pe.mascotapp.colorGrisTittle
import com.pe.mascotapp.colorHeader
import com.pe.mascotapp.colorHorizontal
import com.pe.mascotapp.colorLightGrisTitle
import com.pe.mascotapp.colorMediumBlue
import com.pe.mascotapp.colorPrimary
import com.pe.mascotapp.vistas.extension.toDateFormat
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme
import com.pe.mascotapp.workSansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventHistoryFilterScreen(
    modifier: Modifier = Modifier,
    onClickFilterItem: (String) -> Unit,
    onClickAccept: () -> Unit,
    onClickBack: () -> Unit
) {

    var selectedFilters by remember { mutableStateOf(emptyList<String>()) }

    var selectedStartDate by remember { mutableStateOf("") }
    var selectedEndDate by remember { mutableStateOf("") }
    val openStartDialog = remember { mutableStateOf(false) }
    val openEndDialog = remember { mutableStateOf(false) }

    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Historial",
                    fontFamily = caprasimoFontFamily,
                    fontSize = 24.sp,
                    lineHeight = 27.53.sp,
                    color = colorPrimary,
                    fontWeight = FontWeight.W400,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = onClickBack,
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_chevron_left),
                        contentDescription = null,
                        tint = colorPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            actions = {
                TextButton(
                    onClick = {
                        selectedFilters = emptyList()
                        selectedStartDate = ""
                        selectedEndDate = ""
                    }
                ) {
                    Text(
                        text = "Limpiar",
                        fontFamily = workSansFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 16.42.sp,
                        color = colorPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = colorHeader
            )
        )
        Column(
            modifier = modifier
                .weight(1f)
                .padding(26.dp)
        ) {
            Text(
                text = "Filtrar por",
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W700,
                fontSize = 20.sp,
                lineHeight = 23.46.sp,
                color = colorMediumBlue
            )
            Spacer(modifier = Modifier.height(23.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(5),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filterPreviewData) { filterData ->
                    FilterItem(
                        name = filterData.name,
                        icon = filterData.icon,
                        onClickFilter = {
                            onClickFilterItem(filterData.id)
                            selectedFilters = if (selectedFilters.contains(filterData.id)) {
                                selectedFilters - filterData.id
                            } else {
                                selectedFilters + filterData.id
                            }
                        },
                        isSelected = selectedFilters.contains(filterData.id)
                    )
                }
            }
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "Por fecha",
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 16.64.sp,
                lineHeight = 19.52.sp,
                color = colorGrisTittle
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                onClick = { openStartDialog.value = true },
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = colorLightGrisTitle,
                    disabledContainerColor = colorLightGrisTitle,
                ),
                border = BorderStroke(
                    width = 2.38.dp,
                    color = colorDisabled
                )
            ) {
                Text(
                    text = selectedStartDate.ifEmpty { "Fecha de inicio" },
                    fontFamily = workSansFontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.26.sp,
                    lineHeight = 16.73.sp,
                    color = colorDisabled,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.vector),
                    contentDescription = null,
                    tint = colorDisabled
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedButton(
                onClick = { openEndDialog.value = true },
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = colorLightGrisTitle,
                    disabledContainerColor = colorLightGrisTitle,
                ),
                border = BorderStroke(
                    width = 2.38.dp,
                    color = colorDisabled
                )
            ) {
                Text(
                    text = selectedEndDate.ifEmpty { "Fecha de fin" },
                    fontFamily = workSansFontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.26.sp,
                    lineHeight = 16.73.sp,
                    color = colorDisabled,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.vector),
                    contentDescription = null,
                    tint = colorDisabled
                )
            }
        }
        Button(
            onClick = onClickAccept,
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            contentPadding = PaddingValues(vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorMediumBlue,
                disabledContainerColor = colorMediumBlue
            )
        ) {
            Text(
                text = "Aceptar",
                fontWeight = FontWeight.W400,
                fontSize = 23.sp,
                lineHeight = 27.84.sp,
                color = Color.White
            )
        }
    }

    if (openStartDialog.value) {
        val datePickerState = rememberDatePickerState(
            initialDisplayMode = DisplayMode.Picker
        )
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        openStartDialog.value = false
                        // Todo: Revisar la zona horaria para evitar errores
                        selectedStartDate = datePickerState.selectedDateMillis?.toDateFormat() ?: ""
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openStartDialog.value = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(
                state = datePickerState,
                headline = null,
                title = null,
                showModeToggle = false
            )
        }
    }

    if (openEndDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        openEndDialog.value = false
                        selectedEndDate = datePickerState.selectedDateMillis?.toDateFormat() ?: ""
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openEndDialog.value = false }) { Text("Cancel") }
            }
        ) {
            DatePicker(
                state = datePickerState,
                headline = null,
                title = null,
                showModeToggle = false,
            )
        }
    }
}

@Composable
private fun FilterItem(
    name: String,
    @DrawableRes icon: Int,
    onClickFilter: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClickFilter),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier
                .size(50.dp)
                .background(
                    color = if (isSelected) colorMediumBlue else colorDisabled,
                    shape = RoundedCornerShape(2.5.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                tint = if (isSelected) Color.White else colorHorizontal,
                contentDescription = null,
                modifier = Modifier.padding(6.4.dp)
            )
        }
        Text(
            text = name,
            modifier = Modifier.padding(top = 5.dp),
            fontSize = 9.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 10.56.sp,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun EventHistoryFilterScreenPreview() {
    MascotappTheme {
        EventHistoryFilterScreen(
            onClickFilterItem = {},
            onClickAccept = {},
            onClickBack = {}
        )
    }
}