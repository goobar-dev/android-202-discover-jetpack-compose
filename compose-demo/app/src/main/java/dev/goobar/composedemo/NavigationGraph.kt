package dev.goobar.composedemo

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.ui.config.ScreenConfiguration
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsScreen
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsViewModel
import dev.goobar.composedemo.versionslist.AndroidVersionsListScreen


@Composable
internal fun ComposeDemoNavigationGraph(screenConfiguration: ScreenConfiguration) {
    if (screenConfiguration.isCompact || screenConfiguration.orientation == ScreenConfiguration.Orientation.VERTICAL) {
        PhoneNavigationGraph(screenConfiguration)
    } else {
        TabletNavigationGraph(screenConfiguration.copy(orientation = ScreenConfiguration.Orientation.VERTICAL))
    }
}

@Composable
private fun TabletNavigationGraph(screenConfiguration: ScreenConfiguration) {
    var selectedItem by rememberSaveable {
        mutableStateOf<AndroidVersionInfo?>(null)
    }
    Row(
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            AndroidVersionsListScreen(screenConfiguration) { info ->
                selectedItem = info
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            when (val info = selectedItem) {
                null -> {}
                else -> {
                    AndroidVersionDetailsScreen(
                        viewModel = AndroidVersionDetailsViewModel(info),
                        onBack = {selectedItem = null},
                        showTopBar = false
                    )
                }
            }
        }
    }
}

@Composable fun PhoneNavigationGraph(screenConfiguration: ScreenConfiguration) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DemoNavigationDestinations.VersionsList.route) {
        composable(DemoNavigationDestinations.VersionsList.route) {
            AndroidVersionsListScreen(screenConfiguration) { info ->
                navController.navigate(DemoNavigationDestinations.VersionDetails.createRouteWithArgs(info))
            }
        }
        composable(
            route = DemoNavigationDestinations.VersionDetails.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing)
                )
            }
        ) { entry ->
            val info = DemoNavigationDestinations.VersionDetails.retrieveArgs(entry.arguments)
            AndroidVersionDetailsScreen(
                viewModel = AndroidVersionDetailsViewModel(info),
                onBack = { navController.popBackStack() }
            )
        }
    }
}