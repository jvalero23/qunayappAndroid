package com.pe.mascotapp.vistas.pet_edition

import androidx.lifecycle.ViewModel
import com.pe.mascotapp.domain.models.Sex
import com.pe.mascotapp.vistas.entities.PetEntity
import com.pe.mascotapp.vistas.entities.PetWithBreedsEntity
import com.pe.mascotapp.vistas.fragments.stepRegister.BreedPetEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(

)  : ViewModel() {

    private val _uiState = MutableStateFlow(PetDetailUiState())
    val uiState = _uiState.asStateFlow()

    fun insertPetData(pet: PetWithBreedsEntity) {
        _uiState.update { state ->
            state.copy(
                petObj = pet
            )
        }
    }

    fun setDateToPet(date: Long) {
        val localTime: LocalDateTime = Instant.fromEpochMilliseconds(date).toLocalDateTime(timeZone = TimeZone.UTC)
        val format = LocalDate.Format {
            dayOfMonth()
            char('/')
            monthNumber()
            char('/')
            year()
        }
        val formattedDate = localTime.date.format(format)

        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    pet = state.petObj.pet.copy(
                        birthdate = formattedDate
                    )
                )
            )
        }
    }

    fun updatePetName(name: String) {
        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    pet = state.petObj.pet.copy(
                        name = name
                    )
                )
            )
        }
    }

    fun updatePetSpecie(specie: String) {
        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    pet = state.petObj.pet.copy(
                        specie = specie
                    )
                )
            )
        }
    }

    fun updatePetSex(sex: Sex) {
        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    pet = state.petObj.pet.copy(
                        sex = sex.toString()
                    )
                )
            )
        }
    }

    fun updatePetWeight(weight: String) {
        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    pet = state.petObj.pet.copy(
                        weight = weight
                    )
                )
            )
        }
    }

    fun updatePetBreeds(breeds: List<BreedPetEntity>) {
        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    breeds = breeds
                )
            )
        }
    }

    fun removeBreedFromPet(breedName: String) {
        _uiState.update { state ->
            state.copy(
                petObj = state.petObj.copy(
                    breeds = state.petObj.breeds.filter { it.name != breedName }
                )
            )
        }
    }

}

data class PetDetailUiState(
    val petObj: PetWithBreedsEntity = PetWithBreedsEntity(
        pet = PetEntity(),
        breeds = emptyList()
    )
)