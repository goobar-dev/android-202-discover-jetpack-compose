package dev.goobar.composelabs.domain

import dev.goobar.composelabs.network.SWAPINetworkClient
import dev.goobar.composelabs.network.toPlanet

interface PlanetRepository {
    suspend fun getPlanets(): List<Planet>
}

object NetworkPlanetRepository: PlanetRepository {
    override suspend fun getPlanets(): List<Planet> {
        return SWAPINetworkClient.getPlanets().results.map { it.toPlanet() }
    }
}