package com.pe.mascotapp.vistas.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pe.mascotapp.vistas.fragments.tabsService.TabOne
import com.pe.mascotapp.vistas.fragments.tabsService.TabThree
import com.pe.mascotapp.vistas.fragments.tabsService.TabTwo

class DetailServiceFragmentState(fragmentManager: FragmentManager, context: Context):
    FragmentStatePagerAdapter(fragmentManager)  {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> TabOne()
            1 -> TabTwo()
            2 -> TabThree()
            else -> TabOne()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Info"
            1 -> "Reviews"
            2 -> "Detalles de servicio"
            else -> ""
        }.lowercase()
    }
}
