package com.pe.mascotapp.vistas.extension

import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames

val MONTHS_LOWERCASE_SPANISH_FULL: MonthNames = MonthNames(
    listOf(
        "enero", "febrero", "marzo", "abril", "mayo", "junio",
        "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"
    )
)

val DAY_OF_WEEK_NAMES: DayOfWeekNames = DayOfWeekNames(
    monday = "Lunes",
    tuesday = "Martes",
    wednesday = "Miércoles",
    thursday = "Jueves",
    friday = "Viernes",
    saturday = "Sábado",
    sunday = "Domingo"
)

const val AM_MARKER: String = "a.m."
const val PM_MARKER: String = "p.m."