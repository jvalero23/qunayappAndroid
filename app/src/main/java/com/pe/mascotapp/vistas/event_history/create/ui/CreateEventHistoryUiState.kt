package com.pe.mascotapp.vistas.event_history.create.ui

import android.net.Uri

data class CreateEventHistoryUiState(
    val selectedFilters: List<String> = emptyList(),
    val description: String = "",
    val selectedPetsIds: List<String> = emptyList(),
    val isTimerEnabled: Boolean = false,
    val selectedDate: String = "",
    val selectedDateMillis: Long = 0,
    val imageUris: List<Uri> = emptyList(),
)