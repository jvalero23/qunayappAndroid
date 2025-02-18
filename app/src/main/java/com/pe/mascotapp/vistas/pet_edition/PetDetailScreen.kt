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
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pe.mascotapp.R
import com.pe.mascotapp.bigTitleStyle
import com.pe.mascotapp.buttonTitleStyle
import com.pe.mascotapp.colorCyan
import com.pe.mascotapp.colorDisabled
import com.pe.mascotapp.colorPrimary
import com.pe.mascotapp.domain.models.Sex
import com.pe.mascotapp.vistas.entities.PetEntity
import com.pe.mascotapp.vistas.entities.PetWithBreedsEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.BreedCategory
import com.pe.mascotapp.vistas.fragments.stepRegister.BreedPetEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.CustomChip
import com.pe.mascotapp.vistas.fragments.stepRegister.CustomTextField
import com.pe.mascotapp.vistas.fragments.stepRegister.IconTextButton
import com.pe.mascotapp.vistas.fragments.stepRegister.KindPet
import com.pe.mascotapp.vistas.fragments.stepRegister.PrimaryButton
import com.pe.mascotapp.vistas.fragments.stepRegister.SelectBreedActivity
import com.pe.mascotapp.vistas.fragments.stepRegister.SelectBreedActivity.Companion.BUNDLE_BREED
import com.pe.mascotapp.vistas.fragments.stepRegister.value
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme

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

    var showCalendar by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        Spacer(modifier = Modifier.height(38.dp))
        Box(
            modifier = Modifier
                .size(154.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .background(color = colorResource(R.color.blue_primary)),
            contentAlignment = Alignment.Center
        ) {
            if (pet.pet.image.isNotEmpty()) {
                GlideImage(
                    model = pet.pet.image,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = pet.pet.name.first().toString(),
                    fontSize = 62.sp,
                    style = bigTitleStyle,
                    color = colorCyan
                )
            }
        }
        Spacer(modifier = Modifier.height(42.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp),
            verticalArrangement = Arrangement.spacedBy(18.11.dp)
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = painterResource(id = R.drawable.mascotas),
                value = petName,
                capitalizacion = true,
                onValueChange = {
                    setNameToPet(it)
                },
                label = "¿Cómo se llama tu mascota? "
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                IconTextButton(
                    "Perro",
                    R.drawable.perro,
                    pet.pet.specie == KindPet.Dog.value(),
                    Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .width(IntrinsicSize.Max),
                    onClick = {
                        setSpecieToPet(KindPet.Dog.value())
                    }
                )
                IconTextButton(
                    "Gato",
                    R.drawable.gato,
                    pet.pet.specie == KindPet.Cat.value(),
                    Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .width(IntrinsicSize.Max),
                    onClick = {
                        setSpecieToPet(KindPet.Cat.value())
                    }
                )
                IconTextButton(
                    "Otro",
                    R.drawable.llama,
                    pet.pet.specie == KindPet.Other.value(),
                    Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .width(IntrinsicSize.Max),
                    onClick = { setSpecieToPet(KindPet.Other.value()) }
                )
            }

            if (pet.pet.specie == KindPet.Other.value()) {
                CustomTextField(
                    value = pet.breeds.firstOrNull()?.name ?: "",
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
                    pet = pet,
                    updatePetBreeds = updatePetBreeds,
                    removeBreedFromPet = removeBreedFromPet
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp),
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                IconTextButton(
                    name = "Macho",
                    icon = R.drawable.hombre,
                    isEnabled = pet.pet.sex == Sex.MALE,
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight(),
                    onClick = {
                        setSexToPet(Sex.MALE)
                    }
                )
                IconTextButton(
                    name = "Hembra",
                    icon = R.drawable.mujer,
                    isEnabled = pet.pet.sex == Sex.FEMALE,
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight(),
                    onClick = {
                        setSexToPet(Sex.FEMALE)
                    }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                CustomTextField(
                    modifier = Modifier
                        .weight(1F)
                        .onFocusEvent {
                            if (!it.hasFocus) {
                                val text = pet.pet.weight
                                if (text.isNotEmpty() && text.endsWith('.')) {
                                    updatePetWeight(text.plus("0"))
                                }
                            }
                        },
                    leadingIcon = painterResource(id = R.drawable.peso),
                    value = pet.pet.weight,
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
                    label = "Peso",
                    textAlign = TextAlign.End,
                    keyBoarType = KeyboardType.Decimal,
                    suffix = "kg"
                )
                CustomTextField(
                    modifier = Modifier
                        .weight(1F)
                        .clickable { showCalendar = true },
                    enabled = false,
                    leadingIcon = painterResource(id = R.drawable.edad),
                    value = pet.pet.birthdate,
                    onValueChange = {},
                    label = "Edad",
                    keyBoarType = KeyboardType.Number,
                    leadingIconOnClick = {
                        showCalendar = true
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(64.dp))

        Column {
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(58.dp)
                    .padding(horizontal = 77.dp),
                onClick = onConfirmPetUpdate,
                content = {
                    Text(
                        text = "Actualizar",
                        style = buttonTitleStyle.copy(fontSize = 20.sp)
                    )
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 77.dp),
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                onClick = onCancelPetUpdate
            ) {
                Text(text = "Volver", style = buttonTitleStyle, color = colorPrimary)
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
                    sex = Sex.MALE,
                    birthdate = "ullamcorper",
                    isSelected = false,
                    color = 5740
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