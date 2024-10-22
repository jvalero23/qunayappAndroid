package com.pe.mascotapp.vistas.event_history.create.ui

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Serializable
data object CreateEventHistoryDestination

internal fun NavController.navigateToCreateEventHistoryDestination(
    navOptions: NavOptions? = null
) {
    navigate(route = CreateEventHistoryDestination, navOptions = navOptions)
}

internal fun NavGraphBuilder.createEventHistoryDestination(
    onCreateSuccess: () -> Unit
) {
    composable<CreateEventHistoryDestination> {

        val viewModel: CreateEventHistoryViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        CreateEventHistoryScreen(
            onClickAccept = onCreateSuccess,
            imageUris = uiState.imageUris,
            onImageUrisChanged = viewModel::onImageUrisChanged
        )
    }
}

@HiltViewModel
class CreateEventHistoryViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(CreateEventHistoryUiState())
    val uiState = _uiState.asStateFlow()

    fun onImageUrisChanged(imageUris: List<Uri>) {
        _uiState.update {
            it.copy(imageUris = imageUris)
        }
    }

}

data class CreateEventHistoryUiState(
    val imageUris: List<Uri> = emptyList(),
)