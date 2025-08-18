package com.pe.mascotapp.vistas

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.pe.mascotapp.R

class ComentarioDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comentario_detail)

        val btnBack: LinearLayout = findViewById(R.id.btnBack)

        val backIcon: ImageView = btnBack.findViewById(R.id.ivBackIcon) // Asume que tienes un ImageView con este ID dentro de btnBack.

        backIcon.setOnClickListener {
            onBackPressed()
        }

        // Recibir los datos pasados desde el adaptador
//        val categoriaId = intent.getIntExtra("categoria_id", -1)
//        val titulo = intent.getStringExtra("categoria_titulo").orEmpty()
//        val descripcion = intent.getStringExtra("categoria_desc").orEmpty()
//        val imgKey = intent.getStringExtra("categoria_img").orEmpty()

        // Verificar que los datos se recibieron correctamente
//        if (categoriaId == -1 && titulo.isEmpty() && descripcion.isEmpty()) {
//            Toast.makeText(this, "No se recibieron datos del comentario", Toast.LENGTH_SHORT).show()
//        }

        // Establecer los valores recibidos en los campos correspondientes
        val tvTitulo: TextView = findViewById(R.id.tvTitulo)
        val tvDescripcion: TextView = findViewById(R.id.tvDescripcion)
        val ivPortada: ImageView = findViewById(R.id.ivPortada)

//        tvTitulo.text = titulo
//        tvDescripcion.text = descripcion
//
//        // Cargar la imagen si existe
//        if (imgKey.isNotEmpty()) {
//            val resId = resources.getIdentifier(imgKey, "drawable", packageName)
//            if (resId != 0) {
//                ivPortada.setImageResource(resId)
//            }
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
