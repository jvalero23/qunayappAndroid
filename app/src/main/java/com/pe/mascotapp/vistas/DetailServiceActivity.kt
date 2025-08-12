package com.pe.mascotapp.vistas

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.pe.mascotapp.R
import com.pe.mascotapp.modelos.request.NegocioSede
import com.pe.mascotapp.modelos.request.Sede
import com.pe.mascotapp.utils.Utils
import com.pe.mascotapp.vistas.adapters.ComentariosAdapter
import com.pe.mascotapp.vistas.adapters.SedesAdapter
import com.pe.mascotapp.vistas.fragments.DetailServiceFragmentState


class DetailServiceActivity : AppCompatActivity() {

    var tabLayout:TabLayout ?= null
    var viewPager:ViewPager ?= null
    var btnContactar:Button ?= null
    var sedesAdapterType: SedesAdapter? = null
    var txtSubTitle:TextView ?= null
    var imgNegocio:ImageView ?= null
    lateinit var negociosArray: NegocioSede
    private lateinit var detailServiceAdapter: DetailServiceFragmentState


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_service_activity)
        tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        viewPager = findViewById<ViewPager>(R.id.viewPStep)
        btnContactar = findViewById<Button>(R.id.btnContactar)
        txtSubTitle = findViewById<TextView>(R.id.subTitle)
        imgNegocio = findViewById<ImageView>(R.id.imgNegocio)
        negociosArray = intent.getSerializableExtra("negocioSeleccionado") as NegocioSede

        detailServiceAdapter = DetailServiceFragmentState(
            supportFragmentManager,
            negociosArray,
            negociosArray.sedes[0],
            this
        )
        txtSubTitle!!.text = negociosArray.nombreComercial
        imgNegocio!!.setImageResource(R.drawable.clinica_veterinaria_pancho_cavero)
        viewPager!!.adapter = detailServiceAdapter
        tabLayout!!.setupWithViewPager(viewPager)

        btnContactar!!.setOnClickListener {
            openWhatsappContact("+51969928064")
        }
        showCustomModal()
    }

    fun openWhatsappContact(number: String) {
        val uri = Uri.parse("smsto:$number")
        val i = Intent(Intent.ACTION_SENDTO, uri)
        i.setPackage("com.whatsapp")
        startActivity(Intent.createChooser(i, ""))
    }

    private fun showCustomModal() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val view: View = LayoutInflater.from(this).inflate(R.layout.custom_modal_sedes, null)
        dialog.setContentView(view)

        val tvTitleModal = view.findViewById<TextView>(R.id.tvTitleModal)
        val rcvSedes = view.findViewById<RecyclerView>(R.id.rcvSedes)
        rcvSedes?.setLayoutManager(LinearLayoutManager(this))
        sedesAdapterType = SedesAdapter(negociosArray!!.sedes) { sede ->
            Utils.dump("sede" + sede.direccion)
            detailServiceAdapter.updateData(negociosArray, sede)

            dialog.dismiss()
        }
        rcvSedes?.setAdapter(sedesAdapterType)
        rcvSedes?.setItemAnimator(DefaultItemAnimator())

        tvTitleModal!!.text = "Selecciona la sede"
        /*btnClose.setOnClickListener {
            dialog.dismiss()
        }*/
        dialog.show()

        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.96).toInt(), // ancho 95%
            WindowManager.LayoutParams.WRAP_CONTENT  // alto 80%
        )
        dialog.window?.setGravity(Gravity.CENTER)
    }

    /*fun getSedesEjemplo(): ArrayList<Sede> {
        return arrayListOf(
            Sede(
                id_negocio = 1,
                descripcion = "Sede principal ubicada en el centro",
                fechaRegistro = "2024-05-08T00:47:27.007Z",
                nombre_comercial = "PeruPet",
                tipo_id = 2,
                identificacion = "10765681176",
                razonSocial = "PeruPet S.A.C.",
                id_negocio_sede = 1,
                fecharegistro = "2025-08-05T18:59:38.839Z",
                id_ubigeo = 101010,
                direccion = "Av. Prueba 123, Lima",
                nombre_sede = "Sede Central Qunay"
            ),
            Sede(
                id_negocio = 2,
                descripcion = "Sucursal ubicada en la zona norte",
                fechaRegistro = "2024-06-10T10:15:00.000Z",
                nombre_comercial = "PeruPet Norte",
                tipo_id = 2,
                identificacion = "20456789123",
                razonSocial = "PeruPet Norte S.A.C.",
                id_negocio_sede = 2,
                fecharegistro = "2025-08-06T14:30:00.000Z",
                id_ubigeo = 202020,
                direccion = "Jr. Las Flores 456, Trujillo",
                nombre_sede = "Sede Norte Qunay"
            ),
            Sede(
                id_negocio = 3,
                descripcion = "Sede costera con atención 24 horas",
                fechaRegistro = "2024-07-15T08:00:00.000Z",
                nombre_comercial = "PeruPet Playa",
                tipo_id = 1,
                identificacion = "30123456789",
                razonSocial = "PeruPet Playa S.A.C.",
                id_negocio_sede = 3,
                fecharegistro = "2025-08-07T09:00:00.000Z",
                id_ubigeo = 303030,
                direccion = "Av. Costanera 789, Piura",
                nombre_sede = "Sede Playa Qunay"
            ),
            Sede(
                id_negocio = 4,
                descripcion = "Sucursal en zona sur con servicios especializados",
                fechaRegistro = "2024-08-20T16:45:00.000Z",
                nombre_comercial = "PeruPet Sur",
                tipo_id = 2,
                identificacion = "40112233445",
                razonSocial = "PeruPet Sur S.A.C.",
                id_negocio_sede = 4,
                fecharegistro = "2025-08-08T11:15:00.000Z",
                id_ubigeo = 404040,
                direccion = "Av. Arequipa 234, Arequipa",
                nombre_sede = "Sede Sur Qunay"
            ),
            Sede(
                id_negocio = 5,
                descripcion = "Sede en zona este con centro veterinario",
                fechaRegistro = "2024-09-25T12:30:00.000Z",
                nombre_comercial = "PeruPet Este",
                tipo_id = 1,
                identificacion = "50198765432",
                razonSocial = "PeruPet Este S.A.C.",
                id_negocio_sede = 5,
                fecharegistro = "2025-08-09T17:45:00.000Z",
                id_ubigeo = 505050,
                direccion = "Calle Los Laureles 567, Huancayo",
                nombre_sede = "Sede Este Qunay"
            )
        )
    }*/
}