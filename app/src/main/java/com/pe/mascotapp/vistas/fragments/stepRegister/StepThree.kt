package com.pe.mascotapp.vistas.fragments.stepRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.CarosuelRegisterActivity

class StepThree : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_register_three, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.frtComposeView)
        composeView.setContent {
            StepThreeScreen((activity as? CarosuelRegisterActivity)?.listPets?.toMutableStateList()
                ?: mutableListOf())
        }
        return view;
    }


    companion object {
        fun newInstance(text: String) : StepThree{
            val stepThree = StepThree()
            val args = Bundle()
            args.putString("title", text)
            stepThree.arguments = args
            return stepThree
        }
    }

}