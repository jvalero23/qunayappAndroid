package com.pe.mascotapp.vistas.fragments.home

import androidx.lifecycle.ViewModel
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.adapters.ServiceCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val mockedCategories =  listOf(
            ServiceCategory(
                id = 0,
                name = "Adopción",
                selectedImage = R.drawable.qunay_ilustraciones_app_adoption,
                unselectedImage = R.drawable.qunay_ilustraciones_app_adoption_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 1,
                name = "Hotel",
                selectedImage = R.drawable.qunay_ilustraciones_app_boarding,
                unselectedImage = R.drawable.qunay_ilustraciones_app_boarding_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 2,
                name = "Estética",
                selectedImage = R.drawable.qunay_ilustraciones_app_grooming,
                unselectedImage = R.drawable.qunay_ilustraciones_app_grooming_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 3,
                name = "Guardería",
                selectedImage = R.drawable.qunay_ilustraciones_app_house_sitting,
                unselectedImage = R.drawable.qunay_ilustraciones_app_house_sitting_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 4,
                name = "Marketplace",
                selectedImage = R.drawable.qunay_ilustraciones_app_marketplace,
                unselectedImage = R.drawable.qunay_ilustraciones_app_marketplace_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 5,
                name = "Otros",
                selectedImage = R.drawable.qunay_ilustraciones_app_otros,
                unselectedImage = R.drawable.qunay_ilustraciones_app_otros_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 6,
                name = "Entrenamiento",
                selectedImage = R.drawable.qunay_ilustraciones_app_training,
                unselectedImage = R.drawable.qunay_ilustraciones_app_training_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 7,
                name = "Veterinaria",
                selectedImage = R.drawable.qunay_ilustraciones_app_vet,
                unselectedImage = R.drawable.qunay_ilustraciones_app_vet_gris,
                isSelected = false
            ),
            ServiceCategory(
                id = 8,
                name = "Paseos",
                selectedImage = R.drawable.qunay_ilustraciones_app_walking,
                unselectedImage = R.drawable.qunay_ilustraciones_app_walking_gris,
                isSelected = false
            )
        )
        _uiState.update {
            it.copy(
                serviceCategories = mockedCategories.mapIndexed { index, item ->
                    if (index == 0) {
                        item.copy(isSelected = true)
                    } else {
                        item
                    }
                },
            )
        }
    }

    fun onSelectCategory(categoryId: Int) {
        _uiState.update { uiState ->
            uiState.copy(
                serviceCategories = uiState.serviceCategories.map { category ->
                    if (category.id == categoryId) {
                        category.copy(isSelected = true)
                    } else {
                        category.copy(isSelected = false)
                    }
                }
            )
        }
    }

}

data class HomeUiState(
    val serviceCategories: List<ServiceCategory> = emptyList(),
)