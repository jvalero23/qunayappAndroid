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

class StepThree : Fragment() {

    private val viewModel: CarouselRegisterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_register_three, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.frtComposeView)
        composeView.setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            StepThreeScreen(
                listPets = uiState.listPets,
                removeItemAt = viewModel::removePetAt,
                addNewPet = viewModel::addNewPet,
            )
        }
        return view;
    }


    companion object {
        fun newInstance(text: String): StepThree {
            val stepThree = StepThree()
            val args = Bundle()
            args.putString("title", text)
            stepThree.arguments = args
            return stepThree
        }
    }

}