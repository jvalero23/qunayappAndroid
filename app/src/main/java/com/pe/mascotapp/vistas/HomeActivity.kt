package com.pe.mascotapp.vistas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PowerManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.pe.mascotapp.R
import com.pe.mascotapp.interfaces.PrincipalPresentador
import com.pe.mascotapp.vistas.alert.AlertActivity
import com.pe.mascotapp.vistas.event_history.EventHistoryFragment
import com.pe.mascotapp.vistas.fragments.home.CalendarFragment
import com.pe.mascotapp.vistas.fragments.home.HomeFragment
import com.pe.mascotapp.vistas.fragments.home.PetsFragment
import com.pe.mascotapp.vistas.fragments.home.ReminderFragment
import com.pe.mascotapp.vistas.profile.MessageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    var menuHome: BottomNavigationView? = null
    var toolbar: Toolbar? = null
    var drawer_layout: DrawerLayout? = null
    var toggle: ActionBarDrawerToggle? = null

    var imgBanner: ImageView? = null
    var navigationView: NavigationView? = null
    var iv_notification: ImageView? = null

    private var currentFragment: Fragment? = null
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> selectedFragment = HomeFragment.newInstance()
                R.id.nav_history -> selectedFragment = EventHistoryFragment.newInstance()
                R.id.nav_calendar -> selectedFragment = CalendarFragment.newInstance()
                R.id.nav_pet -> selectedFragment = PetsFragment.newInstance()
                R.id.nav_notification -> selectedFragment = ReminderFragment.newInstance()
                else -> {
                    // Handle the case when none of the cases match
                }
            }
            // Check if the new fragment is different from the current fragment
            if (selectedFragment != null && currentFragment != null && !selectedFragment::class.simpleName.equals(
                    currentFragment?.javaClass?.simpleName
                )
            ) {
                supportFragmentManager.commitNow {
                    replace(R.id.fragmentContainer, selectedFragment)
                }
                // Update the current fragment
                currentFragment = selectedFragment
            }

            //setTitle(getString(title));
            drawer_layout!!.closeDrawer(GravityCompat.START)
            true
        }

    private lateinit var presentador: PrincipalPresentador.VistaStart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        menuHome = findViewById<BottomNavigationView>(R.id.menuHome)
        //imgUser = findViewById<ImageView>(R.id.imgUser)
        imgBanner = findViewById<ImageView>(R.id.imgBanner)
        presentador = PrincipalPresentador.VistaStart(this)
        iv_notification = findViewById<ImageView>(R.id.iv_notification)
        //checkBattery(this)
        //obtenerData()
        //startRCVHome()
        iniciarvista(savedInstanceState)
    }

    private fun iniciarvista(savedInstanceState: Bundle?) {
        menuHome = findViewById<BottomNavigationView>(R.id.menuHome)
        navigationView = findViewById<NavigationView>(R.id.navigationView)
        drawer_layout = findViewById<DrawerLayout>(R.id.drawer_layout)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        val imgDogBanner = findViewById<ShapeableImageView>(R.id.imgDogBanner)
        menuHome!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        startMenu(savedInstanceState)

        val navViewBackground = navigationView?.background as MaterialShapeDrawable
        navViewBackground.shapeAppearanceModel = navViewBackground.shapeAppearanceModel
            .toBuilder()
            .setBottomRightCorner(CornerFamily.ROUNDED, 528f)
            .build()


        val shapeAppearanceModel = imgDogBanner.shapeAppearanceModel
            .toBuilder()
            .setBottomRightCorner(CornerFamily.ROUNDED, 528f) // Adjust corner size as needed
            .build()

        imgDogBanner.shapeAppearanceModel = shapeAppearanceModel
        // navigationView!!.setNavigationItemSelectedListener(onNavigationItemSelectedListener)
        startMenu(savedInstanceState)
        val nav_perfil = findViewById<TextView>(R.id.nav_perfil)
        val nav_message = findViewById<TextView>(R.id.nav_message)
        nav_perfil.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        nav_message.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        }
        iv_notification?.setOnClickListener {
            val intent = Intent(this, AlertActivity::class.java)
            startActivity(intent)
        }
        defineToggle()

    }

    fun defineToggle() {
        toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).also {
            drawer_layout?.addDrawerListener(it)
            it.syncState()
        }
    }

    private fun startMenu(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            val selectedFragment = HomeFragment.newInstance()
            val transaction =
                supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainer, selectedFragment)
            transaction.commit()
            currentFragment = selectedFragment
            //toolTitle!!.text = "Hola, " + "Usuario"
            //menuHome!!.selectedItemId = R.id.nav_home
            //navigationView!!.setCheckedItem(R.id.nav_menu_principal)
        }

    }

    private fun isBatteryOptimized(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val name = context.packageName
        return !powerManager.isIgnoringBatteryOptimizations(name)
    }

}
