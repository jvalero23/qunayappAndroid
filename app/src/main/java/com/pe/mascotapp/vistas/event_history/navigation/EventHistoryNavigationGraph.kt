package com.pe.mascotapp.vistas.event_history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.pe.mascotapp.vistas.event_history.create.ui.createEventHistoryDestination
import com.pe.mascotapp.vistas.event_history.create.ui.navigateToCreateEventHistoryDestination
import com.pe.mascotapp.vistas.event_history.filter.ui.eventHistoryFilterDestination
import com.pe.mascotapp.vistas.event_history.filter.ui.navigateToEventHistoryFilterDestination
import com.pe.mascotapp.vistas.event_history.main.ui.EventHistoryDestination
import com.pe.mascotapp.vistas.event_history.main.ui.eventHistoryDestination
import kotlinx.serialization.Serializable

@Serializable
data object EventHistoryNavigationGraph

fun NavController.navigateToEventHistoryGraph(navOptions: NavOptions? = null) {
    navigate(EventHistoryNavigationGraph, navOptions)
}

fun NavGraphBuilder.eventHistoryGraphDestination(
    navController: NavController,
) {
    navigation<EventHistoryNavigationGraph>(
        startDestination = EventHistoryDestination
    ) {
        eventHistoryDestination(
            navToFilter = navController::navigateToEventHistoryFilterDestination,
            navToDetail = {

            },
            onAddHistory = navController::navigateToCreateEventHistoryDestination
        )
        eventHistoryFilterDestination(
            onClickAccept = navController::popBackStack,
            onClickBack = navController::popBackStack
        )
        createEventHistoryDestination(
            onCreateSuccess = navController::popBackStack
        )
    }
}