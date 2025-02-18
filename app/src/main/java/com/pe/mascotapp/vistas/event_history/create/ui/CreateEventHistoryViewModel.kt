package com.pe.mascotapp.vistas.event_history.create.ui

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CreateEventHistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args: CreateEventHistoryDestination =
        savedStateHandle.toRoute<CreateEventHistoryDestination>()

    private val _uiState = MutableStateFlow(CreateEventHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        if (args.isEdit) {
            // Load data from database
            _uiState.update {
                CreateEventHistoryUiState(
                    imageUris = listOf(
                        Uri.parse("android.resource://com.pe.mascotapp/drawable/perro1")
                    ),
                    selectedFilters = listOf("VACCINE"),
                    description = "Descripción de la historia",
                    selectedPetsIds = listOf("1"),
                    isTimerEnabled = true,
                    selectedDate = "Martes 24 julio, 10:22",
                )
            }
        }
    }

    fun onImageUrisChanged(imageUris: List<Uri>) {
        _uiState.update {
            it.copy(imageUris = imageUris)
        }
    }

    fun onSelectedFiltersChanged(selectFilterId: String) {
        _uiState.update {
            val selectedFilters = if (it.selectedFilters.contains(selectFilterId)) {
                it.selectedFilters - selectFilterId
            } else {
                it.selectedFilters + selectFilterId
            }
            it.copy(selectedFilters = selectedFilters)
        }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun onSelectedPetsChanged(id: String) {
        _uiState.update {
            val selectedPetsIds = if (it.selectedPetsIds.contains(id)) {
                it.selectedPetsIds - id
            } else {
                it.selectedPetsIds + id
            }
            it.copy(selectedPetsIds = selectedPetsIds)
        }
    }

    fun onToggleAddTimer() {
        _uiState.update {
            it.copy(isTimerEnabled = !it.isTimerEnabled)
        }
    }

    fun onSelectedDateChanged(date: String, millis: Long) {
        _uiState.update {
            it.copy(selectedDate = date, selectedDateMillis = millis)
        }
    }

}