package com.pe.mascotapp.vistas.event_history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.pe.mascotapp.R
import com.pe.mascotapp.vistas.event_history.navigation.EventHistoryNavigationGraph
import com.pe.mascotapp.vistas.event_history.navigation.eventHistoryGraphDestination
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
                    // Now define your NavHost using type safe objects
                    NavHost(
                        navController,
                        startDestination = EventHistoryNavigationGraph
                    ) {
                        eventHistoryGraphDestination(navController)
                    }
                }
            }
        }
        return view
    }

    companion object {
        fun newInstance() : Fragment{
            return EventHistoryFragment()
        }
    }

}