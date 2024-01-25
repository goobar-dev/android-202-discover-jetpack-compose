package dev.goobar.composedemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.composedemo.data.AndroidVersionsRepository
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsScreen
import dev.goobar.composedemo.versionslist.AndroidVersionsListScreen

@Composable
internal fun ComposeDemoNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DemoNavigationDestinations.VersionsList.route) {
        composable(DemoNavigationDestinations.VersionsList.route) {
            AndroidVersionsListScreen(AndroidVersionsRepository.data) { info ->
                navController.navigate(DemoNavigationDestinations.VersionDetails.createRouteWithArgs(info))
            }
        }
        composable(route = DemoNavigationDestinations.VersionDetails.route) { entry ->
            val info = DemoNavigationDestinations.VersionDetails.retrieveArgs(entry.arguments)
            AndroidVersionDetailsScreen(info) {
                navController.popBackStack()
            }
        }
    }
}