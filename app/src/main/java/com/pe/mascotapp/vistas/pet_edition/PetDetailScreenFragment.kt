package com.pe.mascotapp.vistas.pet_edition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.entities.PetWithBreedsEntity
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme

class PetDetailScreenFragment : Fragment() {

    val viewModel by viewModels<PetDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            val pet = requireArguments().getParcelable<PetWithBreedsEntity>(PET_KEY)!!
            viewModel.insertPetData(pet)
            setContent {
                MascotappTheme {
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    Box(
                        Modifier
                            .background(Color.White)
                            .fillMaxSize()
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(R.drawable.background_patitas),
                            contentDescription = "background_patitas",
                            contentScale = ContentScale.FillBounds
                        )
                        PetDetailScreen(
                            pet = uiState.petObj,
                            setNameToPet = viewModel::updatePetName,
                            setSpecieToPet = viewModel::updatePetSpecie,
                            updatePetBreeds = viewModel::updatePetBreeds,
                            removeBreedFromPet = viewModel::removeBreedFromPet,
                            setSexToPet = viewModel::updatePetSex,
                            updatePetWeight = viewModel::updatePetWeight,
                            setDateToPet = viewModel::setDateToPet,
                            onConfirmPetUpdate = {
                                // Aqui debemos mandar la actualizacion
                                this@PetDetailScreenFragment.parentFragmentManager.popBackStack()
                            },
                            onCancelPetUpdate = {
                                this@PetDetailScreenFragment.parentFragmentManager.popBackStack()
                            }
                        )
                    }

                }
            }
        }
    }

    companion object {
        private const val PET_KEY = "petEntity"

        @JvmStatic
        fun newInstance(petWithBreedsEntity: PetWithBreedsEntity) =
            PetDetailScreenFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(PET_KEY, petWithBreedsEntity)
                }
            }
    }

}