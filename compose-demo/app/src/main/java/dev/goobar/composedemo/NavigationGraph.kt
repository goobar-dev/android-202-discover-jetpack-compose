package dev.goobar.composedemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.goobar.composedemo.data.AndroidVersionsRepository
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsScreen
import dev.goobar.composedemo.versionslist.AndroidVersionsListScreen

@Composable
internal fun ComposeDemoNavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "versionsList") {
        composable("versionsList") {
            AndroidVersionsListScreen(AndroidVersionsRepository.data) { info ->
                navController.navigate("versionDetails/${info.apiVersion}")
            }
        }
        composable(
            route = "versionDetails/{apiVersion}",
            arguments = listOf(navArgument("apiVersion") { type = NavType.IntType })
        ) { entry ->
            val apiVersion = entry.arguments?.getInt("apiVersion") ?: throw IllegalStateException("missing apiVersion")
            AndroidVersionDetailsScreen(apiVersion) {
                navController.popBackStack()
            }
        }
    }
}