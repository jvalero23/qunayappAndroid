package com.pe.mascotapp.vistas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.color.MaterialColors.getColor
import com.pe.mascotapp.R
import com.pe.mascotapp.databinding.ActivityPetDetailBinding
import com.pe.mascotapp.vistas.entities.PetEntity
import com.pe.mascotapp.vistas.pet_edition.EditPetDetailActivity

class PetDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPetDetailBinding
    private lateinit var petEntity: PetEntity

    companion object {
        private const val REQ_PICK_IMAGE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.subTitle.text = getString(R.string.my_pets)
        binding.toolbar.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val extra = intent.getParcelableExtra("petEntity") as? PetEntity
        petEntity = extra ?: PetEntity()

        setUpValues()
        setListeners()

        binding.btnAddPhoto.setOnClickListener {
            openGallery()
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
        }
        startActivityForResult(intent, REQ_PICK_IMAGE)
    }

    @Deprecated("Deprecated in Android 13+, usado por compatibilidad con minSdk 26")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            if (uri != null) addPhotoUri(uri)
        }
    }

    private fun setUpValues() {
        binding.petEntity = petEntity
        binding.executePendingBindings()

        Glide.with(binding.root.context)
            .load(petEntity.image)
            .placeholder(R.drawable.perro1)
            .error(R.drawable.perro1)
            .into(binding.petImage)
    }

    private fun addPhotoUri(uri: Uri) {
        val rows = binding.galleryRows
        val lastRow = rows.getChildAt(rows.childCount - 1) as LinearLayout
        val isLastRowAddButton = (binding.btnAddPhoto.parent as LinearLayout) == lastRow
        if (isLastRowAddButton && lastRow.childCount == 1) {
            val card = buildCard()
            lastRow.addView(card, 0)
            setImageInto(card, uri)
            return
        }
        if (isLastRowAddButton && lastRow.childCount == 2) {
            rows.removeView(binding.btnAddPhoto.parent as LinearLayout)
            val row = newRow()
            rows.addView(row)
            val card = buildCard()
            row.addView(card)
            row.addView(binding.btnAddPhoto.parent as MaterialCardView)
            setImageInto(card, uri)
            return
        }
        if (!isLastRowAddButton && lastRow.childCount < 2) {
            val card = buildCard()
            lastRow.addView(card)
            setImageInto(card, uri)
        } else {
            val row = newRow()
            rows.addView(row)
            val card = buildCard()
            row.addView(card)
            setImageInto(card, uri)
        }
    }

    private fun newRow(): LinearLayout =
        LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            weightSum = 2f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

    private fun buildCard(): MaterialCardView =
        MaterialCardView(this).apply {
            layoutParams = LinearLayout.LayoutParams(0, dp(150), 1f).apply {
                setMargins(dp(4), dp(4), dp(4), dp(4))
            }
            radius = dp(10).toFloat()
            cardElevation = 0f
            strokeWidth = dp(10)
            strokeColor = getColor(R.color.primaryColor)
        }

    private fun setImageInto(card: MaterialCardView, uri: Uri) {
        val iv = androidx.appcompat.widget.AppCompatImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
        }
        Glide.with(this).load(uri).placeholder(R.drawable.perro1).into(iv)
        card.addView(iv)
    }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    private fun setListeners() {
        // Al hacer clic en el botón de editar, abre la nueva actividad
        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditPetDetailActivity::class.java)  // Asegúrate de crear esta actividad
            startActivity(intent)
        }
    }
}
