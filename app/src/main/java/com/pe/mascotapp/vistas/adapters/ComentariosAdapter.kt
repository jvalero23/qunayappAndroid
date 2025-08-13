package com.pe.mascotapp.vistas.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.pe.mascotapp.R
import com.pe.mascotapp.modelos.Categorias
import com.pe.mascotapp.vistas.ComentarioDetailActivity

class ComentariosAdapter(
    private val categorias: ArrayList<Categorias>,
    private var listener: (Categorias) -> Unit
) : RecyclerView.Adapter<ComentariosAdapter.ComentariosAdapterHolder>() {

    private var categoriasList = ArrayList<Categorias>()

    init { categoriasList = categorias }

    inner class ComentariosAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mostrarMas: LinearLayout = view.findViewById(R.id.linearMostrarMas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentariosAdapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comentario_holder, parent, false)
        return ComentariosAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: ComentariosAdapterHolder, position: Int) {
        val item = categoriasList[position]
        holder.mostrarMas.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ComentarioDetailActivity::class.java).apply {
                putExtra("categoria_id", item.id)
                putExtra("categoria_titulo", item.titulo)
                putExtra("categoria_desc", item.descripcion)
                putExtra("categoria_img", item.img)
            }
            context.startActivity(intent)
        }
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = categoriasList.size
}
