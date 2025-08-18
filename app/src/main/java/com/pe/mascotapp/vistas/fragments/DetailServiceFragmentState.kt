package com.pe.mascotapp.vistas.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pe.mascotapp.modelos.request.NegocioSede
import com.pe.mascotapp.modelos.request.Sede
import com.pe.mascotapp.vistas.fragments.tabsService.TabOne
import com.pe.mascotapp.vistas.fragments.tabsService.TabThree
import com.pe.mascotapp.vistas.fragments.tabsService.TabTwo

class DetailServiceFragmentState (fragmentManager: FragmentManager,
                                  private var data: NegocioSede,
                                  private  var sedeSeleccionada: Sede,
                                  context: Context)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return TabOne.newInstance(data, sedeSeleccionada)
            }
            1 -> {
                return TabTwo()
            }
            2 -> {
                return TabOne.newInstance(data, sedeSeleccionada)
            }
            else -> return TabOne.newInstance(data, sedeSeleccionada)
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Info"
            1 -> "Reviews"
            2 -> "Detalles de servicio"
            else -> ""
        }.lowercase()
    }

    fun updateData(newData: NegocioSede, newSede: Sede) {
        data = newData
        sedeSeleccionada = newSede
        notifyDataSetChanged()
    }
}
