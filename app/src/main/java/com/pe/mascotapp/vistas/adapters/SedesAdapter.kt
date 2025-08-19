package com.pe.mascotapp.vistas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pe.mascotapp.R
import com.pe.mascotapp.modelos.request.Sede

class SedesAdapter (private val sedes:ArrayList<Sede>, private var listener:(Sede)->Unit ):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var sedeList = ArrayList<Sede>()

    init {
        sedeList = sedes
    }

    class SedesAdapterHolder(view: View): RecyclerView.ViewHolder(view){
        val txtText: TextView

        init {
            txtText = view.findViewById(R.id.txtText)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sede_holder,parent,false)
        return SedesAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as SedesAdapterHolder
        holder.txtText.text = sedeList[position].nombreSede + " - " + sedeList[position].direccion;
        holder.itemView.setOnClickListener { listener(sedeList[position]) }

    }

    override fun getItemCount(): Int {
        return sedeList.size
    }

}