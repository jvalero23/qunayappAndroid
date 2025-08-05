package com.pe.mascotapp.vistas.event_history.main.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.dropUnlessResumed
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
) {
    composable<EventHistoryDestination> {
        val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
        EventHistoryScreen(
            onClickSeeAll = {
                if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
                    navToDetail(it)
                }
            },
            onClickSeeFilter = navToFilter,
            onAddHistory = dropUnlessResumed {
                onAddHistory()
            }
        )
    }
}

