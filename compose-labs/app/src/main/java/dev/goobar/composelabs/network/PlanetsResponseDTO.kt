package dev.goobar.composelabs.network

import kotlinx.serialization.Serializable

@Serializable
class PlanetsResponseDTO(val results: List<PlanetDTO>)
