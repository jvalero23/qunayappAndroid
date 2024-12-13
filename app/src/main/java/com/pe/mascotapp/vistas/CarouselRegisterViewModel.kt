package com.pe.mascotapp.vistas

import androidx.lifecycle.ViewModel
import com.pe.mascotapp.domain.models.Sex
import com.pe.mascotapp.vistas.entities.PetEntity
import com.pe.mascotapp.vistas.entities.PetWithBreedsEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.BreedPetEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.getColorIndex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

class CarouselRegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun addPet(pet: PetWithBreedsEntity) {
        val listPets = _uiState.value.listPets.toMutableList()
        listPets.add(0, pet)
        _uiState.value = _uiState.value.copy(listPets = listPets)
    }

    fun removePetAt(index: Int) {
        _uiState.update {
            it.copy(
                listPets = it.listPets.filterIndexed { pos, _ ->
                    pos != index
                }
            )
        }
    }

    fun setDateToPet(index: Int, date: String) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(
                            pet = petWithDetails.pet.copy(
                                birthdate = date
                            )
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun updatePetName(index: Int, name: String) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(
                            pet = petWithDetails.pet.copy(
                                name = name
                            )
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun updatePetSpecie(index: Int, specie: String) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        val newBreeds =
                            if (petWithDetails.pet.specie != specie) emptyList() else petWithDetails.breeds
                        petWithDetails.copy(
                            pet = petWithDetails.pet.copy(specie = specie),
                            breeds = newBreeds
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun updatePetSex(index: Int, sex: Sex) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(
                            pet = petWithDetails.pet.copy(sex = sex)
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun updatePetWeight(index: Int, weight: String) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(
                            pet = petWithDetails.pet.copy(weight = weight)
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun updatePetBirthdate(index: Int, birthdate: String) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(
                            pet = petWithDetails.pet.copy(birthdate = birthdate)
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun updatePetBreeds(index: Int, breeds: List<BreedPetEntity>) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(breeds = breeds)
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun removeBreedFromPet(index: Int, breedName: String) {
        _uiState.update { state ->
            state.copy(
                listPets = state.listPets.mapIndexed { i, petWithDetails ->
                    if (i == index) {
                        petWithDetails.copy(
                            breeds = petWithDetails.breeds.filter { it.name != breedName }
                        )
                    } else {
                        petWithDetails
                    }
                }
            )
        }
    }

    fun addNewPet() {
        _uiState.update { state ->
            state.copy(
                listPets = listOf(
                    PetWithBreedsEntity(
                        PetEntity(color = getColorIndex(state.listPets.size)),
                        listOf()
                    )
                ) + state.listPets
            )
        }
    }

}

data class RegisterUiState(
    val listPets: List<PetWithBreedsEntity> = listOf(
        PetWithBreedsEntity(
            PetEntity(),
            listOf()
        )
    )
)