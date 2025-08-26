package com.pe.mascotapp.vistas.pet_edition

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pe.mascotapp.R

class EditPetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pet_detail)

        val btnClose: LinearLayout = findViewById(R.id.lnlClose)
        val btnBack: LinearLayout = findViewById(R.id.lnlBack)
        val ivClose: ImageView = findViewById(R.id.ivClose)
        val ivBack: ImageView = findViewById(R.id.ivBack)
        val btnMacho = findViewById<LinearLayout>(R.id.btn_macho)
        val btnHembra = findViewById<LinearLayout>(R.id.btn_hembra)
        val txtMacho = findViewById<TextView>(R.id.txt_macho)
        val txtHembra = findViewById<TextView>(R.id.txt_hembra)
        val spinnerRaza: Spinner = findViewById(R.id.spinner_raza)

        val razas = listOf("Labrador", "Bulldog", "Beagle", "Poodle", "Chihuahua")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, razas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRaza.adapter = adapter

        btnMacho.setOnClickListener {
            btnMacho.isSelected = true
            btnHembra.isSelected = false
            txtMacho.setTextColor(ContextCompat.getColor(this, R.color.white))
            txtHembra.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
        }

        btnHembra.setOnClickListener {
            btnHembra.isSelected = true
            btnMacho.isSelected = false
            txtMacho.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
            txtHembra.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        val btnEsterilizadoSi = findViewById<LinearLayout>(R.id.btn_EsterilizadoSi)
        val btnEsterilizadoNo = findViewById<LinearLayout>(R.id.btn_EsterilizadoNo)
        val txtEsterilizadoSi = findViewById<TextView>(R.id.txt_esterilizadosi)
        val txtEsterilizadoNo = findViewById<TextView>(R.id.txt_esterilizadono)

        btnEsterilizadoSi.setOnClickListener {
            btnEsterilizadoSi.isSelected = true
            btnEsterilizadoNo.isSelected = false
            txtEsterilizadoSi.setTextColor(ContextCompat.getColor(this, R.color.white))
            txtEsterilizadoNo.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
        }

        btnEsterilizadoNo.setOnClickListener {
            btnEsterilizadoNo.isSelected = true
            btnEsterilizadoSi.isSelected = false
            txtEsterilizadoSi.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
            txtEsterilizadoNo.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        val btnEntrenadoSi = findViewById<LinearLayout>(R.id.btn_EntrenadoSi)
        val btnEntrenadoNo = findViewById<LinearLayout>(R.id.btn_EntrenadoNo)
        val txtEntrenadoSi = findViewById<TextView>(R.id.txt_entrenadosi)
        val txtEntrenadoNo = findViewById<TextView>(R.id.txt_entrenadono)

        btnEntrenadoSi.setOnClickListener {
            btnEntrenadoSi.isSelected = true
            btnEntrenadoNo.isSelected = false
            txtEntrenadoSi.setTextColor(ContextCompat.getColor(this, R.color.white))
            txtEntrenadoNo.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
        }

        btnEntrenadoNo.setOnClickListener {
            btnEntrenadoNo.isSelected = true
            btnEntrenadoSi.isSelected = false
            txtEntrenadoSi.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
            txtEntrenadoNo.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        val btnAmigableSi = findViewById<LinearLayout>(R.id.btn_AmigableSi)
        val btnAmigableNo = findViewById<LinearLayout>(R.id.btn_AmigableNo)
        val txtAmigableSi = findViewById<TextView>(R.id.txt_amigablesi)
        val txtAmigableNo = findViewById<TextView>(R.id.txt_amigableno)

        btnAmigableSi.setOnClickListener {
            btnAmigableSi.isSelected = true
            btnAmigableNo.isSelected = false
            txtAmigableSi.setTextColor(ContextCompat.getColor(this, R.color.white))
            txtAmigableNo.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
        }

        btnAmigableNo.setOnClickListener {
            btnAmigableNo.isSelected = true
            btnAmigableSi.isSelected = false
            txtAmigableSi.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
            txtAmigableNo.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        val btnAmigableNinosSi = findViewById<LinearLayout>(R.id.btn_AmigableNinosSi)
        val btnAmigableNinosNo = findViewById<LinearLayout>(R.id.btn_AmigableNinosNo)
        val txtAmigableNinosSi = findViewById<TextView>(R.id.txt_amigableninossi)
        val txtAmigableNinosNo = findViewById<TextView>(R.id.txt_amigableninosno)

        btnAmigableNinosSi.setOnClickListener {
            btnAmigableNinosSi.isSelected = true
            btnAmigableNinosNo.isSelected = false
            txtAmigableNinosSi.setTextColor(ContextCompat.getColor(this, R.color.white))
            txtAmigableNinosNo.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
        }

        btnAmigableNinosNo.setOnClickListener {
            btnAmigableNinosNo.isSelected = true
            btnAmigableNinosSi.isSelected = false
            txtAmigableNinosSi.setTextColor(ContextCompat.getColor(this, R.color.gris_flecha))
            txtAmigableNinosNo.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        btnClose.setOnClickListener {
            finish()
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, EditPetDetailActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
