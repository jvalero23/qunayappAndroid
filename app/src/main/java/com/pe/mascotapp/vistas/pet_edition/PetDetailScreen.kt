package com.pe.mascotapp.vistas.pet_edition

import android.app.Activity
import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.pe.mascotapp.R
import com.pe.mascotapp.buttonTitleStyle
import com.pe.mascotapp.colorDisabled
import com.pe.mascotapp.colorMediumBlue
import com.pe.mascotapp.colorPrimary
import com.pe.mascotapp.domain.models.Sex
import com.pe.mascotapp.grayLight
import com.pe.mascotapp.vistas.entities.PetEntity
import com.pe.mascotapp.vistas.entities.PetWithBreedsEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.BreedCategory
import com.pe.mascotapp.vistas.fragments.stepRegister.BreedPetEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.CustomChip
import com.pe.mascotapp.vistas.fragments.stepRegister.CustomTextField
import com.pe.mascotapp.vistas.fragments.stepRegister.KindPet
import com.pe.mascotapp.vistas.fragments.stepRegister.PrimaryButton
import com.pe.mascotapp.vistas.fragments.stepRegister.SelectBreedActivity
import com.pe.mascotapp.vistas.fragments.stepRegister.SelectBreedActivity.Companion.BUNDLE_BREED
import com.pe.mascotapp.vistas.fragments.stepRegister.value
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme
import com.pe.mascotapp.workSansFontFamily

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PetDetailScreen(
    pet: PetWithBreedsEntity,
    setNameToPet: (String) -> Unit,
    setSpecieToPet: (String) -> Unit,
    updatePetBreeds: (List<BreedPetEntity>) -> Unit,
    removeBreedFromPet: (name: String) -> Unit,
    setSexToPet: (sex: Sex) -> Unit,
    updatePetWeight: (String) -> Unit,
    setDateToPet: (Long) -> Unit,
    onConfirmPetUpdate: () -> Unit,
    onCancelPetUpdate: () -> Unit,
    modifier: Modifier = Modifier
) {
    val petName: String = pet.pet.name
    val scrollState = rememberScrollState()
    val onChangePhoto: () -> Unit = {}

    var showCalendar by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = pet.pet.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop,
                placeholder = if (LocalInspectionMode.current) painterResource(R.drawable.perro1) else null
            )
            Button(
                onClick = onChangePhoto,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = colorPrimary
                ),
                contentPadding = PaddingValues(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_camara),
                    contentDescription = null,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "Cambiar foto",
                    fontFamily = workSansFontFamily,
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
            }
        }
        GeneralInformationSection(
            petEntity = pet.pet,
            breeds = pet.breeds,
            setNameToPet = setNameToPet,
            updatePetBreeds = updatePetBreeds,
            removeBreedFromPet = removeBreedFromPet,
            setDateToPet = setDateToPet,
            updatePetWeight = updatePetWeight,
            setSexToPet = setSexToPet,
            modifier = Modifier,
        )
        HorizontalDivider()
        CareInformationSection()
        HorizontalDivider()
        HealthInformationSection()
        HorizontalDivider()
        PhotoGallerySection(
            imageUris = emptyList()
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(58.dp)
                    .padding(horizontal = 77.dp),
                onClick = {},
                content = {
                    Text(
                        text = "Guardar",
                        style = buttonTitleStyle.copy(fontSize = 20.sp)
                    )
                })

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 77.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                onClick = {}
            ) {
                Text(text = "Cancelar", style = buttonTitleStyle, color = colorPrimary)
            }
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
    }

    if (showCalendar) {
        DatePickerModal(
            onDateSelected = setDateToPet,
            onDismiss = { showCalendar = false }
        )
    }
}

@Composable
fun GeneralInformationSection(
    petEntity: PetEntity,
    breeds: List<BreedPetEntity>,
    setNameToPet: (String) -> Unit,
    updatePetBreeds: (List<BreedPetEntity>) -> Unit,
    removeBreedFromPet: (name: String) -> Unit,
    setDateToPet: (Long) -> Unit,
    updatePetWeight: (String) -> Unit,
    setSexToPet: (sex: Sex) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showCalendar by remember { mutableStateOf(false) }
    Column(modifier) {
        Text(
            text = "Información General",
            fontSize = 26.sp,
            fontFamily = workSansFontFamily,
            fontWeight = FontWeight.W700,
            color = colorPrimary
        )
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Nombre",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            CustomTextField(
                value = petEntity.name,
                onValueChange = setNameToPet,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "Raza",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            if (petEntity.specie == KindPet.Other.value()) {
                CustomTextField(
                    value = breeds.firstOrNull()?.name ?: "",
                    onValueChange = {
                        updatePetBreeds(
                            listOf(
                                BreedPetEntity(
                                    category = BreedCategory.OTHER,
                                    name = it
                                )
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = "¿Cuál es su especie?",
                    leadingIcon = painterResource(R.drawable.estrella)
                )
            } else {
                ChipGroup(
                    pet = PetWithBreedsEntity(
                        petEntity,
                        breeds
                    ),
                    updatePetBreeds = updatePetBreeds,
                    removeBreedFromPet = removeBreedFromPet
                )
            }
            Text(
                text = "Edad",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            CustomTextField(
                modifier = Modifier
                    .clickable { showCalendar = true }
                    .fillMaxWidth(),
                enabled = false,
                value = petEntity.birthdate,
                onValueChange = {},
            )
            // Peso
            Text(
                text = "Peso (kg)",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusEvent {
                        if (!it.hasFocus) {
                            val text = petEntity.weight
                            if (text.isNotEmpty() && text.endsWith('.')) {
                                updatePetWeight(text.plus("0"))
                            }
                        }
                    },
                value = petEntity.weight,
                onValueChange = { value ->
                    var filteredText = value.filter { it.isDigit() || it == '.' }
                    filteredText.also {
                        if (it.isNotEmpty() && it.startsWith(".")) return@CustomTextField
                        if (it.count { char -> char == '.' } > 1) return@CustomTextField
                        if (it.length > 9) return@CustomTextField
                    }
                    filteredText = filteredText.trimStart('0').ifEmpty { "0" }
                    if (filteredText.startsWith(".")) filteredText = "0$filteredText"
                    updatePetWeight(filteredText)
                },
                keyBoarType = KeyboardType.Decimal,
            )

            // Gender
            Text(
                text = "Género",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            Row {
                SelectableChip(
                    isSelected = petEntity.sex == Sex.MALE.toString(),
                    onSelect = { setSexToPet(Sex.MALE) },
                    labelText = "Macho",
                    painter = painterResource(R.drawable.hombre)
                )
                Spacer(modifier = Modifier.width(12.dp))
                SelectableChip(
                    isSelected = petEntity.sex == Sex.FEMALE.toString(),
                    onSelect = { setSexToPet(Sex.FEMALE) },
                    labelText = "Hembra",
                    painter = painterResource(R.drawable.mujer)
                )
            }
            // Esterilizado
            Text(
                text = "Esterilizado",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            var isSterilized by remember { mutableStateOf(true) }
            Row {
                SelectableChip(
                    isSelected = isSterilized,
                    onSelect = { isSterilized = true },
                    labelText = "Sí",
                )
                Spacer(modifier = Modifier.width(12.dp))
                SelectableChip(
                    isSelected = !isSterilized,
                    onSelect = { isSterilized = false },
                    labelText = "No",
                )
            }
            // Entrenado
            Text(
                text = "Entrenado",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            var isTrained by remember { mutableStateOf(true) }
            Row {
                SelectableChip(
                    isSelected = isTrained,
                    onSelect = { isTrained = true },
                    labelText = "Sí",
                )
                Spacer(modifier = Modifier.width(12.dp))
                SelectableChip(
                    isSelected = !isTrained,
                    onSelect = { isTrained = false },
                    labelText = "No",
                )
            }
            // Amigable con otros perros
            Text(
                text = "Amigable con otros perros",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            var isFriendlyWithDogs by remember { mutableStateOf(true) }
            Row {
                SelectableChip(
                    isSelected = isFriendlyWithDogs,
                    onSelect = { isFriendlyWithDogs = true },
                    labelText = "Sí",
                )
                Spacer(modifier = Modifier.width(12.dp))
                SelectableChip(
                    isSelected = !isFriendlyWithDogs,
                    onSelect = { isFriendlyWithDogs = false },
                    labelText = "No",
                )
            }
            // Amable con ninos
            Text(
                text = "Amable con niños:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            var isFriendlyWithKids by remember { mutableStateOf(true) }
            Row {
                SelectableChip(
                    isSelected = isFriendlyWithKids,
                    onSelect = { isFriendlyWithKids = true },
                    labelText = "Sí",
                )
                Spacer(modifier = Modifier.width(12.dp))
                SelectableChip(
                    isSelected = !isFriendlyWithKids,
                    onSelect = { isFriendlyWithKids = false },
                    labelText = "No",
                )
            }
        }
    }
    if (showCalendar) {
        DatePickerModal(
            onDateSelected = setDateToPet,
            onDismiss = { showCalendar = false }
        )
    }
}

@Preview
@Composable
private fun GeneralInformationSectionPreview() {
    GeneralInformationSection(
        petEntity = PetEntity(
            petId = null,
            image = "",
            name = "Anuel",
            specie = "",
            weight = "10",
            sex = Sex.MALE.toString(),
            birthdate = "12/12/2021",
            isSelected = false,
            color = 0
        ),
        breeds = listOf(),
        setNameToPet = {},
        updatePetBreeds = {},
        removeBreedFromPet = {},
        setDateToPet = {},
        updatePetWeight = {},
        setSexToPet = {},

        )
}

@Composable
fun CareInformationSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Información de cuidado",
            fontSize = 26.sp,
            fontFamily = workSansFontFamily,
            fontWeight = FontWeight.W700,
            color = colorPrimary
        )
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Salidas al baño:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            val frequencyOptions = listOf(
                "Cada 2 horas",
                "Cada 4 horas",
                "Cada 6 horas",
                "Cada 8 horas",
                "Cada 10 horas",
                "Cada 12 horas"
            )
            var selectedFrequency by remember { mutableStateOf(frequencyOptions.first()) }
            SelectableDropdown(
                options = frequencyOptions,
                selectedOption = selectedFrequency,
                onSelectionChange = { selectedFrequency = it }
            )

            Text(
                text = "Nivel de energia:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            val energyOptions = listOf(
                "Bajo",
                "Medio",
                "Alto"
            )
            var selectedEnergy by remember { mutableStateOf(energyOptions.first()) }
            SelectableDropdown(
                options = energyOptions,
                selectedOption = selectedEnergy,
                onSelectionChange = { selectedEnergy = it }
            )
            Text(
                text = "Horarios de comida:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            val foodOptions = listOf(
                "Cada 4 horas",
                "Cada 6 horas",
                "Cada 8 horas",
                "Cada 10 horas",
                "Cada 12 horas"
            )
            var selectedFood by remember { mutableStateOf(foodOptions.first()) }
            SelectableDropdown(
                options = foodOptions,
                selectedOption = selectedFood,
                onSelectionChange = { selectedFood = it }
            )
            Text(
                text = "Informacion adicional:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            var additionalInfo by remember { mutableStateOf("") }
            CustomTextField(
                value = additionalInfo,
                onValueChange = { additionalInfo = it },
                label = "",
                minLines = 3,
                singleLine = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SelectableDropdown(
    options: List<String>,
    selectedOption: String,
    onSelectionChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopStart)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Text(text = selectedOption, modifier = Modifier.fillMaxWidth())

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectionChange(option) // Notifica al padre el nuevo valor seleccionado
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun CareInformationSectionPreview() {
    MascotappTheme {
        CareInformationSection()
    }
}

@Composable
fun HealthInformationSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Información de salud",
            fontSize = 26.sp,
            fontFamily = workSansFontFamily,
            fontWeight = FontWeight.W700,
            color = colorPrimary
        )
        Spacer(modifier = Modifier.height(14.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Vacunas:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            val mealTimeOptions = listOf(
                "Si",
                "No"
            )
            var selectedMealTime by remember { mutableStateOf(mealTimeOptions.first()) }
            SelectableDropdown(
                options = mealTimeOptions,
                selectedOption = selectedMealTime,
                onSelectionChange = { selectedMealTime = it }
            )
            Text(
                text = "Detalles adicionales:",
                fontSize = 18.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W600,
                color = colorMediumBlue
            )
            var additionalDetails by remember { mutableStateOf("") }
            CustomTextField(
                value = additionalDetails,
                onValueChange = { additionalDetails = it },
                label = "",
                minLines = 3,
                singleLine = false,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun HealthInformationSectionPreview() {
    MascotappTheme {
        HealthInformationSection()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PhotoGallerySection(
    imageUris: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        ) {
            imageUris.forEach { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = if (LocalInspectionMode.current) painterResource(R.drawable.perro1) else null
                )
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier.size(180.dp),
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                    Text(
                        text = "Agregar fotos",
                        fontFamily = workSansFontFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}

@Preview
@Composable
private fun PhotoGallerySectionPreview() {
    MascotappTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            PhotoGallerySection(
                imageUris = listOf("s", "fe", "f")
            )
        }
    }
}

@Composable
fun SelectableChip(
    isSelected: Boolean,
    onSelect: () -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    painter: Painter? = null,
) {
    FilterChip(
        modifier = modifier,
        selected = isSelected,
        onClick = onSelect,
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp,
            borderColor = grayLight,
        ),
        label = {
            Text(
                text = labelText,
                fontSize = 16.sp,
                fontFamily = workSansFontFamily,
                fontWeight = FontWeight.W400,
            )
        },
        leadingIcon = painter?.let {
            {
                Icon(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            labelColor = grayLight,
            iconColor = grayLight,
            selectedContainerColor = colorPrimary,
            selectedLabelColor = Color.White,
            selectedLeadingIconColor = Color.White,

            )
    )
}

@Preview
@Composable
private fun SelectableChipPreview() {
    MascotappTheme {
        var isSelected by remember { mutableStateOf(false) }
        SelectableChip(
            labelText = "Macho",
            painter = painterResource(R.drawable.hombre),
            isSelected = isSelected,
            onSelect = { isSelected = !isSelected }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { onDateSelected(it) }
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ChipGroup(
    pet: PetWithBreedsEntity,
    updatePetBreeds: (breeds: List<BreedPetEntity>) -> Unit,
    removeBreedFromPet: (name: String) -> Unit
) {
    val context = LocalContext.current

    val breedLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            updatePetBreeds(
                (
                        result.data
                            ?.getParcelableArrayExtra(BUNDLE_BREED)
                            ?.filterIsInstance<BreedPetEntity>()
                            ?: emptyList()
                        )
            )
        }
    }
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(9.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth(1F)
            .border(
                border = BorderStroke(width = 1.81.dp, colorDisabled),
                shape = RoundedCornerShape(9.dp)
            )
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clickable {
                if (pet.pet.specie != KindPet.None.value() && pet.pet.specie != KindPet.Other.value()) {
                    breedLauncher.launch(
                        SelectBreedActivity.newInstance(
                            context as Activity,
                            pet.breeds,
                            pet.pet.specie
                        )
                    )
                }
            }
    ) {
        Image(
            painter = painterResource(R.drawable.estrella),
            contentDescription = "",
            modifier = Modifier.height(20.dp)
        )
        if (pet.breeds.isEmpty()) {
            Text(
                text = "¿Cuál es su raza?",
                style = buttonTitleStyle.copy(fontSize = 14.sp, color = colorDisabled),
            )
        } else {
            pet.breeds.forEach { breed ->
                CustomChip(breed.name) {
                    removeBreedFromPet(breed.name)
                }
            }
        }

    }
}

@Preview
@Composable
private fun PetDetailScreenPreview() {
    MascotappTheme {
        PetDetailScreen(
            pet = PetWithBreedsEntity(
                pet = PetEntity(
                    petId = null,
                    name = "Anna Morales",
                    specie = "vocent",
                    weight = "14",
                    sex = Sex.MALE.toString(),
                    birthdate = "ullamcorper",
                    isSelected = false,
                    color = 5740,
                    image = "android.resource://com.pe.mascotapp/drawable/perro1".toUri().toString()
                ), breeds = listOf()

            ),
            setNameToPet = {},
            setSpecieToPet = {},
            updatePetBreeds = {},
            removeBreedFromPet = {},
            setSexToPet = {},
            updatePetWeight = {},
            setDateToPet = {},
            onConfirmPetUpdate = {},
            onCancelPetUpdate = {},
            modifier = Modifier
        )
    }
}