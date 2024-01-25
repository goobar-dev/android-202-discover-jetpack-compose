package dev.goobar.composelabs.navigation

import dev.goobar.composelabs.domain.Planet

object StarWarsPlanetsNavigationDestinations {
    object PlanetsList : NavigationDestination
    object PlanetDetails : ArgumentDestination<Planet>
}