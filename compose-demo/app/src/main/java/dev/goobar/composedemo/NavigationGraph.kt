package dev.goobar.composedemo

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsScreen
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsViewModel
import dev.goobar.composedemo.versionslist.AndroidVersionsListScreen

@Composable
internal fun ComposeDemoNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DemoNavigationDestinations.VersionsList.route) {
        composable(DemoNavigationDestinations.VersionsList.route) {
            AndroidVersionsListScreen { info ->
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
            AndroidVersionDetailsScreen(AndroidVersionDetailsViewModel(info)) {
                navController.popBackStack()
            }
        }
    }
}