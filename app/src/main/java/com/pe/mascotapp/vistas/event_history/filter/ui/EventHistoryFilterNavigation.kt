package com.pe.mascotapp.vistas.event_history.filter.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object EventHistoryFilterDestination

internal fun NavController.navigateToEventHistoryFilterDestination(
    navOptions: NavOptions? = null
) {
    navigate(route = EventHistoryFilterDestination, navOptions = navOptions)
}

internal fun NavGraphBuilder.eventHistoryFilterDestination(
    onClickAccept: () -> Unit,
    onClickBack: () -> Unit
) {
    composable<EventHistoryFilterDestination> {
        EventHistoryFilterScreen(
            onClickFilterItem = {},
            onClickAccept = onClickAccept,
            onClickBack = onClickBack
        )
    }
}