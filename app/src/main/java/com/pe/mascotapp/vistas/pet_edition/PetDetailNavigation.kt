package com.pe.mascotapp.vistas.pet_edition

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object PetDetailNavigation

internal fun NavGraphBuilder.petDetailDestination(

) {
    composable<PetDetailNavigation> {
        val viewModel = hiltViewModel<PetDetailViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        PetDetailScreen(
            pet = uiState.petObj,
            setNameToPet = viewModel::updatePetName,
            setSpecieToPet = viewModel::updatePetSpecie,
            updatePetBreeds = viewModel::updatePetBreeds,
            removeBreedFromPet = viewModel::removeBreedFromPet,
            setSexToPet = viewModel::updatePetSex,
            updatePetWeight = viewModel::updatePetWeight,
            setDateToPet = viewModel::setDateToPet,
            onConfirmPetUpdate = {},
            onCancelPetUpdate = {}
        )
    }
}