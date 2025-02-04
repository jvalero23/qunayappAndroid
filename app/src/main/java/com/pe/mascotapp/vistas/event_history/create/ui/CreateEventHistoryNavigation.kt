package com.pe.mascotapp.vistas.event_history.create.ui

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data class CreateEventHistoryDestination(
    val editable: Boolean
)

internal fun NavController.navigateToCreateEventHistoryDestination(
    editable: Boolean,
    navOptions: NavOptions? = null
) {
    navigate(route = CreateEventHistoryDestination(editable = editable), navOptions = navOptions)
}

internal fun NavGraphBuilder.createEventHistoryDestination(
    onCreateSuccess: () -> Unit
) {
    composable<CreateEventHistoryDestination> {

        val (editable) = it.toRoute<CreateEventHistoryDestination>()
        val viewModel: CreateEventHistoryViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CreateEventHistoryScreen(
            editable = editable,
            onClickAccept = dropUnlessResumed(block = onCreateSuccess),
            imageUris = uiState.imageUris,
            onImageUrisChanged = viewModel::onImageUrisChanged,
            selectedFilters = uiState.selectedFilters,
            onClickFilter = viewModel::onSelectedFiltersChanged,
            description = uiState.description,
            onDescriptionChanged = viewModel::onDescriptionChanged,
            selectedPetsIds = uiState.selectedPetsIds,
            onClickPet = viewModel::onSelectedPetsChanged,
            isTimerEnabled = uiState.isTimerEnabled,
            onToggleAddTimer = viewModel::onToggleAddTimer,
            selectedDate = uiState.selectedDate,
            onSelectedDateChanged = viewModel::onSelectedDateChanged
        )
    }
}

@HiltViewModel
class CreateEventHistoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args: CreateEventHistoryDestination =
        savedStateHandle.toRoute<CreateEventHistoryDestination>()

    private val _uiState = MutableStateFlow(CreateEventHistoryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        if (!args.editable) {
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
        if (!args.editable) return
        _uiState.update {
            it.copy(imageUris = imageUris)
        }
    }

    fun onSelectedFiltersChanged(selectFilterId: String) {
        if (!args.editable) return
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
        if (!args.editable) return
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun onSelectedPetsChanged(id: String) {
        if (!args.editable) return
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
        if (!args.editable) return
        _uiState.update {
            it.copy(isTimerEnabled = !it.isTimerEnabled)
        }
    }

    fun onSelectedDateChanged(date: String, millis: Long) {
        if (!args.editable) return
        _uiState.update {
            it.copy(selectedDate = date, selectedDateMillis = millis)
        }
    }

}

data class CreateEventHistoryUiState(
    val selectedFilters: List<String> = emptyList(),
    val description: String = "",
    val selectedPetsIds: List<String> = emptyList(),
    val isTimerEnabled: Boolean = false,
    val selectedDate: String = "",
    val selectedDateMillis: Long = 0,
    val imageUris: List<Uri> = emptyList(),
)