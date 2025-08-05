package com.pe.mascotapp.vistas.event_history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.HomeActivity
import com.pe.mascotapp.vistas.event_history.navigation.EventHistoryNavigationGraph
import com.pe.mascotapp.vistas.event_history.navigation.eventHistoryGraphDestination
import com.pe.mascotapp.vistas.event_history.navigation.routesWithTopBars
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme

class EventHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_event_history, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)

        composeView.apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                // In Compose world
                MascotappTheme {
                    val navController = rememberNavController()

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    navBackStackEntry?.destination?.let { currentDestination ->
                        val isRouteWithToolbar: Boolean =
                            routesWithTopBars.find { currentDestination.hasRoute(it) } != null

                        if (isRouteWithToolbar) {
                            changeBottomBarVisibility(true)
                            changeToolbarToBackButton(false)
                        } else {
                            changeBottomBarVisibility(false)
                            changeToolbarToBackButton(
                                true,
                                backAction = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                    // Now define your NavHost using type safe objects
                    NavHost(
                        navController,
                        startDestination = EventHistoryNavigationGraph
                    ) {
                        eventHistoryGraphDestination(
                            navController
                        )
                    }
                }
            }
        }
        return view
    }

    companion object {
        fun newInstance(): Fragment {
            return EventHistoryFragment()
        }
    }

    private fun changeBottomBarVisibility(isVisible: Boolean) {
        val homeActivity: HomeActivity? = (activity as? HomeActivity)
        if (isVisible) {
            homeActivity?.menuHome?.visibility = View.VISIBLE
        } else {
            homeActivity?.menuHome?.visibility = View.GONE
        }
    }

    private fun changeToolbarToBackButton(isWithBackIcon: Boolean, backAction: () -> Unit = {}) {
        val homeActivity: HomeActivity = (activity as? HomeActivity) ?: return
        if (isWithBackIcon) {
            // Desactivamos el icono de hamburguesa y colocamos el ícono de retroceso por defecto de Android
            homeActivity.toggle?.isDrawerIndicatorEnabled = false
            val backIcon = AppCompatResources.getDrawable(
                homeActivity,
                androidx.appcompat.R.drawable.abc_ic_ab_back_material
            )
            backIcon?.setTint(ContextCompat.getColor(homeActivity, android.R.color.white))
            homeActivity.toolbar?.navigationIcon = backIcon
            // Asignamos el callback para la acción de retroceso
            homeActivity.toolbar?.setNavigationOnClickListener {
                backAction.invoke()
            }
        } else {
            // Restauramos el ícono de hamburguesa por defecto
            homeActivity.toggle?.isDrawerIndicatorEnabled = true
            homeActivity.toggle?.syncState()
            homeActivity.defineToggle()
        }
    }

}