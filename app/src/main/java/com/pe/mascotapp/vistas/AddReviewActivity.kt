package com.pe.mascotapp.vistas

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pe.mascotapp.R
import com.pe.mascotapp.databinding.AddReviewActivityBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

class AddReviewActivity : AppCompatActivity() {

    private lateinit var binding: AddReviewActivityBinding
    private var rating = 0  // Para llevar el conteo de las estrellas seleccionadas

    private val IMAGE_REQUEST_CODE = 1000
    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddReviewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarEstrellas()

        // Configuración del botón de regresar
        val btnBack: ImageView = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }

        // Lógica para la sección de fotos
        binding.layoutFoto.setOnClickListener {
            openGallery()
        }

        // Contador de caracteres para el título
        binding.edtTitulo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val charCount = s?.length ?: 0
                binding.txtTituloCount.text = "$charCount/50"
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Contador de caracteres para la reseña
        binding.edtResena.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val charCount = s?.length ?: 0
                binding.txtResenaCount.text = "$charCount/1000"
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Lógica para el botón "Publicar"
        binding.btnPublicar.setOnClickListener {
            saveReviewData()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.data!!
            binding.imageView.setImageURI(selectedImageUri)
            binding.layoutFoto.findViewById<TextView>(R.id.txtFotos).visibility = View.GONE
        }
    }

    private fun saveReviewData() {
        // Calcular el número de estrellas seleccionadas
        val stars = listOf(binding.star1, binding.star2, binding.star3, binding.star4, binding.star5)

        // Cada estrella llena es +1, cada estrella vacía es -1
        val filledStars = stars.count { it.drawable.constantState == resources.getDrawable(R.drawable.ic_review_paw).constantState }
        val emptyStars = stars.size - filledStars
        val totalRating = filledStars - emptyStars // Resultado final con lógica de +1 y -1

        val title = binding.edtTitulo.text.toString()
        val review = binding.edtResena.text.toString()

        // Guardar la imagen seleccionada si existe
        val selectedImagePath = if (::selectedImageUri.isInitialized) {
            saveImageToGallery(selectedImageUri)
        } else {
            "No image"
        }

        // Mostrar mensaje con los datos guardados (en una implementación real, guardas estos datos)
        Toast.makeText(
            this,
            "Guardado: Título: $title, Reseña: $review, Calificación: $totalRating estrellas, Imagen guardada: $selectedImagePath",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun saveImageToGallery(uri: Uri): String {
        try {
            val inputStream = contentResolver.openInputStream(uri)
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, "ReseñaImagen_${System.currentTimeMillis()}")
                put(MediaStore.Images.Media.DISPLAY_NAME, "ReseñaImagen_${System.currentTimeMillis()}.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MascotApp/")
            }

            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            val outputStream = contentResolver.openOutputStream(uri!!)

            val byteArray = inputStream?.readBytes()
            outputStream?.write(byteArray)
            outputStream?.close()

            return uri.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            return "Error al guardar la imagen"
        }
    }

    private fun configurarEstrellas() {
        val estrellas = listOf(binding.star1, binding.star2, binding.star3, binding.star4, binding.star5)

        estrellas.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                rating = index + 1
                actualizarEstrellas(estrellas)
            }
        }
    }

    private fun actualizarEstrellas(estrellas: List<ImageView>) {
        estrellas.forEachIndexed { index, imageView ->
            if (index < rating) {
                imageView.setImageResource(R.drawable.ic_review_paw) // Estrella llena
            } else {
                imageView.setImageResource(R.drawable.ic_review_pawblank) // Estrella vacía
            }
        }
    }
}
