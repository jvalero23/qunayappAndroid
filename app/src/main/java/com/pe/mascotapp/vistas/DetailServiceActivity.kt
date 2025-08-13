package com.pe.mascotapp.vistas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.fragments.DetailServiceFragmentState

class DetailServiceActivity : AppCompatActivity() {

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var btnContactar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_service_activity)

        val btnBack: LinearLayout = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.viewPStep)
        btnContactar = findViewById(R.id.btnContactar)

        // Configuración del ViewPager y TabLayout
        viewPager!!.adapter = DetailServiceFragmentState(supportFragmentManager, this)
        tabLayout!!.setupWithViewPager(viewPager)

        // Configuración del botón "Contactar"
        btnContactar!!.setOnClickListener {
            openWhatsappContact("+51969928064")
        }
    }

    // Método para abrir WhatsApp con el número de contacto
    fun openWhatsappContact(number: String) {
        val uri = Uri.parse("smsto:$number")
        val i = Intent(Intent.ACTION_SENDTO, uri)
        i.setPackage("com.whatsapp")
        startActivity(Intent.createChooser(i, ""))
    }
}
