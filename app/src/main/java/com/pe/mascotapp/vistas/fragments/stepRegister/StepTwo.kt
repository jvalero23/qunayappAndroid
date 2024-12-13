package com.pe.mascotapp.vistas.fragments.stepRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.CarouselRegisterViewModel


class StepTwo : Fragment() {

    private val viewModel: CarouselRegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_register_two, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.frtComposeView)
        composeView.setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            StepTwoScreen(
                uiState.listPets,
                removeItemAt = viewModel::removePetAt,
                addPet = viewModel::addPet,
                setDateToPet = viewModel::setDateToPet,
                setNameToPet = viewModel::updatePetName,
                setSpecieToPet = viewModel::updatePetSpecie,
                setSexToPet = viewModel::updatePetSex,
                updatePetWeight = viewModel::updatePetWeight,
                updatePetBirthdate = viewModel::updatePetBirthdate,
                updatePetBreeds = viewModel::updatePetBreeds,
                removeBreedFromPet = viewModel::removeBreedFromPet,
            )
        }
        return view
    }


    companion object {
        fun newInstance(text: String): StepTwo {
            val stepTwo = StepTwo()
            val args = Bundle()
            args.putString("title", text)
            stepTwo.arguments = args
            return stepTwo
        }
    }

}

