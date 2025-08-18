package com.pe.mascotapp.vistas.fragments.tabsService

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pe.mascotapp.R
import com.pe.mascotapp.modelos.request.NegocioSede
import com.pe.mascotapp.modelos.request.Sede

class TabOne : Fragment() {

    private lateinit var negocioData: NegocioSede
    private lateinit var sedeData: Sede

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            negocioData = it.getSerializable("negocioData") as NegocioSede
            sedeData = it.getSerializable("sedeData") as Sede
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_step_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var txtDireccion = view.findViewById<TextView>(R.id.txtDireccion)
        var txtDescripcion = view.findViewById<TextView>(R.id.txtDescripcion)
        txtDireccion.text = sedeData.direccion
        txtDescripcion.text = sedeData.descripcion

    }

    companion object {
        fun newInstance(data: NegocioSede, sede: Sede): TabOne {
            val fragment = TabOne()
            val args = Bundle().apply {
                putSerializable("negocioData", data)
                putSerializable("sedeData", sede)
            }
            fragment.arguments = args
            return fragment
        }
    }
}