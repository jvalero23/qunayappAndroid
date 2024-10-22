package com.pe.mascotapp.vistas.event_history.create.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pe.mascotapp.R
import com.pe.mascotapp.colorDarkGrisTittle
import com.pe.mascotapp.colorDisabled
import com.pe.mascotapp.colorGrisTittle
import com.pe.mascotapp.colorHorizontal
import com.pe.mascotapp.colorMediumBlue
import com.pe.mascotapp.vistas.event_history.filter.ui.filterPreviewData
import com.pe.mascotapp.vistas.extension.clearFocusOnKeyboardDismiss
import com.pe.mascotapp.vistas.extension.debugPlaceholder
import com.pe.mascotapp.vistas.extension.toShortDateWithTime
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme
import com.pe.mascotapp.workSansFontFamily

@Composable
fun CreateEventHistoryScreen(
    onClickAccept: () -> Unit,
    imageUris: List<Uri>,
    onImageUrisChanged: (List<Uri>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val contentPadding by remember { mutableStateOf(16.dp) }
    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                PetSelectionSection(modifier = Modifier.fillMaxWidth())
            }
            item {
                FilterSection(
                    modifier = Modifier
                        .padding(horizontal = contentPadding)
                )
            }
            item {
                DescriptionSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = contentPadding)
                )
            }
            item {
                TimerSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = contentPadding)
                )
            }
            item {
                PhotosSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = contentPadding),
                    selectedUris = onImageUrisChanged,
                    imageUris = imageUris
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
}

@Composable
fun PhotosSection(
    modifier: Modifier = Modifier,
    selectedUris: (List<Uri>) -> Unit,
    imageUris: List<Uri>,
) {
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = selectedUris
    )

    fun launchPhotoPicker() {
        multiplePhotoPickerLauncher.launch(
            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
        )
    }

    val maxColumns = 2
    val rows = imageUris.chunked(maxColumns)

    Column(
        modifier = modifier
    ) {
        TextButton(
            onClick = { launchPhotoPicker() },
            colors = ButtonDefaults.textButtonColors(
                contentColor = colorGrisTittle
            )
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
            Text("Agregar fotos")
        }
        Spacer(modifier = Modifier.height(8.dp))

        for (row in rows) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)) {
                for ((index, item) in row.withIndex()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item)
                            .build(),
                        contentDescription = null,
                        placeholder = debugPlaceholder(R.drawable.perro1),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(134.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(2.5.dp))
                            .padding(end = if (index < row.size - 1) 8.dp else 0.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerSection(
    modifier: Modifier = Modifier,
) {
    var isTimerEnabled by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("Martes 24 julio, 10:22") }
    val openSelectDateDialog = remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "Agregar horario",
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                fontSize = 16.64.sp,
                lineHeight = 19.52.sp,
                color = colorGrisTittle
            )
            Switch(
                checked = isTimerEnabled,
                onCheckedChange = { isTimerEnabled = it }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = selectedDate,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W400,
                fontSize = 16.64.sp,
                lineHeight = 19.52.sp,
                color = colorMediumBlue
            )
            TextButton(
                onClick = { openSelectDateDialog.value = true }
            ) {
                Text(
                    text = "Cambiar fecha",
                    fontFamily = workSansFontFamily,
                    fontWeight = FontWeight.W600,
                    fontSize = 16.64.sp,
                    lineHeight = 19.52.sp,
                    color = colorMediumBlue
                )
            }
        }
    }

    if (openSelectDateDialog.value) {
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
                        openSelectDateDialog.value = false
                        // Todo: Revisar la zona horaria para evitar errores
                        selectedDate =
                            datePickerState.selectedDateMillis?.toShortDateWithTime() ?: ""
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openSelectDateDialog.value = false }) { Text("Cancel") }
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
}

@Composable
fun DescriptionSection(
    modifier: Modifier = Modifier
) {
    var description: String by remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Descripción",
            fontSize = 16.64.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 19.52.sp,
            color = colorGrisTittle,
            fontFamily = workSansFontFamily
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Black.copy(0.48f),
                focusedBorderColor = Color.Black.copy(0.48f),
            ),
            modifier = Modifier
                .clearFocusOnKeyboardDismiss()
                .fillMaxWidth(),
            minLines = 5,
            textStyle = TextStyle(
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W300,
                fontSize = 16.64.sp,
                lineHeight = 19.52.sp,
                color = colorGrisTittle
            )
        )
    }
}

@Composable
fun FilterSection(
    modifier: Modifier = Modifier
) {
    var selectedFilters by remember { mutableStateOf(emptyList<String>()) }

    val filterData = filterPreviewData

    val maxColumns = 5
    val rows = filterData.chunked(maxColumns)

    Column(modifier = modifier) {
        for (row in rows) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for ((index, item) in row.withIndex()) {
                    FilterItem(
                        name = item.name,
                        icon = item.icon,
                        onClickFilter = {
                            selectedFilters = if (selectedFilters.contains(item.id)) {
                                selectedFilters - item.id
                            } else {
                                selectedFilters + item.id
                            }
                        },
                        isSelected = selectedFilters.contains(item.id),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = if (index < row.size - 1) 8.dp else 0.dp)
                    )
                }
            }
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
    val iconColor = if (isSelected) Color.White else colorHorizontal
    val backgroundColor = if (isSelected) colorMediumBlue else colorDisabled

    Column(
        modifier = modifier
            .clickable(onClick = onClickFilter),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(2.5.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                tint = iconColor,
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

@Composable
fun PetSelectionSection(
    modifier: Modifier = Modifier,
) {
    val selectedIds = remember { mutableStateListOf<String>() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color = colorHorizontal)
            .padding(vertical = 24.dp)
    ) {
        Row {
            Text(
                text = "Selecciona mascota",
                fontSize = 16.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                lineHeight = 18.77.sp
            )
            Text(
                text = " (${selectedIds.size})",
                fontSize = 16.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                lineHeight = 18.77.sp,
                color = colorMediumBlue
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = sampleDataPet, key = { it.id }) {
                SelectablePet(
                    petName = it.name,
                    petType = it.type,
                    imageUri = it.imageUri,
                    isSelected = selectedIds.contains(it.id),
                    modifier = Modifier.clickable {
                        if (selectedIds.contains(it.id)) {
                            selectedIds.remove(it.id)
                        } else {
                            selectedIds.add(it.id)
                        }
                    }
                )
            }
        }

    }
}

@Composable
fun SelectablePet(
    petName: String,
    petType: String,
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    isSelected: Boolean = false
) {
    val grayFilter = remember {
        mutableStateOf(ColorFilter.colorMatrix(ColorMatrix().apply {
            setToSaturation(0f)
        }))
    }
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .build(),
            contentDescription = null,
            placeholder = debugPlaceholder(R.drawable.perro1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(82.dp)
                .clip(CircleShape)
                .then(
                    if (isSelected) Modifier.border(
                        2.dp,
                        color = Color.Black,
                        CircleShape
                    ) else Modifier
                ),
            colorFilter = if (!isSelected) grayFilter.value else null

        )
        Spacer(modifier = Modifier.height(7.2.dp))
        Text(
            text = petName,
            fontSize = 11.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 13.28.sp,
            color = colorDarkGrisTittle,
            fontFamily = workSansFontFamily
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = petType,
            fontSize = 10.29.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 12.07.sp,
            color = colorDisabled,
            fontFamily = workSansFontFamily
        )
    }
}

@Preview
@Composable
private fun CreateEventHistoryScreenPreview() {
    MascotappTheme {
        CreateEventHistoryScreen(
            onClickAccept = {},
            imageUris = emptyList(),
            onImageUrisChanged = {}
        )
    }
}