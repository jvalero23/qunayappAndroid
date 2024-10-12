package com.pe.mascotapp.vistas.event_history.filter.ui

import androidx.annotation.DrawableRes
import com.pe.mascotapp.R

data class FilterData(
    val id: String,
    val name: String,
    @DrawableRes val icon: Int,
)

val filterPreviewData = listOf(
    FilterData(
        "VACCINE",
        "Vacuna",
        R.drawable.ic_vaccine,
    ),
    FilterData(
        "DEWORNING",
        "Desparasitación",
        R.drawable.ic_bacterias,
    ),
    FilterData(
        "VET",
        "Veterinario",
        R.drawable.ic_vet,
    ),
    FilterData(
        "ANALYSIS",
        "Análisis",
        R.drawable.ic_analysis,
    ),
    FilterData(
        "MEDICINE",
        "Medicina",
        R.drawable.ic_medicine,
    ),
    FilterData(
        "DENTAL",
        "Profilaxis Dental",
        R.drawable.ic_dental_prophylaxis,
    ),
    FilterData(
        "WALK",
        "Paseo",
        R.drawable.ic_walk,
    ),
    FilterData(
        "TAKESHOWER",
        "Baño y Corte",
        R.drawable.ic_take_shower,
    ),
    FilterData(
        "WATERFOOD",
        "Agua y Comida",
        R.drawable.ic_water_food,
    ),
    FilterData(
        "OTHERS",
        "Otros",
        R.drawable.ic_others,
    ),

)