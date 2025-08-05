package com.pe.mascotapp.vistas.event_history.create.ui

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class CreateEventHistoryDestination(
    val isEdit: Boolean
)

internal fun NavController.navigateToCreateEventHistoryDestination(
    isEdit: Boolean,
    navOptions: NavOptions? = null
) {
    navigate(route = CreateEventHistoryDestination(isEdit = isEdit), navOptions = navOptions)
}

internal fun NavGraphBuilder.createEventHistoryDestination(
    onCreateSuccess: () -> Unit
) {
    composable<CreateEventHistoryDestination> {
        val viewModel: CreateEventHistoryViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CreateEventHistoryScreen(
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

