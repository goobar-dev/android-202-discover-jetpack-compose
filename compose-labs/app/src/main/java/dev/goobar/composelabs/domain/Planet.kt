package dev.goobar.composelabs.domain

import dev.goobar.composelabs.navigation.NavigationArgs
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val rotationPeriod: Int = 0,
    val orbitalPeriod: Int = 0,
    val diameter: Int = 0,
    val climate: String = "",
    val gravity: String = "",
    val population: String = "",
    val url: String = ""
): NavigationArgs