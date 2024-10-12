package com.pe.mascotapp.vistas.event_history.main.ui

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object EventHistoryDestination

internal fun NavController.navigateToEventHistoryDestination(
    navOptions: NavOptions? = null
) {
    navigate(route = EventHistoryDestination, navOptions = navOptions)
}

internal fun NavGraphBuilder.eventHistoryDestination(
    navToFilter: () -> Unit,
    navToDetail: (String) -> Unit,
    onAddHistory: () -> Unit
){
    composable<EventHistoryDestination> {
        val context = LocalContext.current
        EventHistoryScreen(
            onClickSeeAll = {
                navToDetail(it)
                Toast.makeText(
                    context,
                    "Navigate to detail",
                    Toast.LENGTH_SHORT
                ).show()
            },
            onClickSeeFilter = navToFilter,
            onAddHistory = {
                onAddHistory()
                Toast.makeText(
                    context,
                    "Add history",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}

