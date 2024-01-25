package dev.goobar.composelabs.domain

import dev.goobar.composelabs.navigation.NavigationArgs
import kotlinx.serialization.Serializable

@Serializable
data class Planet(
    val name: String,
    val rotationPeriod: Int,
    val orbitalPeriod: Int,
    val diameter: Int,
    val climate: String,
    val gravity: String,
    val population: String,
    val url: String
): NavigationArgs