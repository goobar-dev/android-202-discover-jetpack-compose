package dev.goobar.composelabs.planetslist

import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.goobar.composelabs.domain.NetworkPlanetRepository
import dev.goobar.composelabs.domain.Planet
import dev.goobar.composelabs.domain.PlanetRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus

class PlanetListViewModel(private val repository: PlanetRepository): ViewModel() {

    val state = flow { emit(repository.getPlanets()) }
        .map { State(it.toImmutableList()) }
        .stateIn(viewModelScope + Dispatchers.IO, SharingStarted.WhileSubscribed(5_000), State(persistentListOf()))

    data class State(
        val planets: ImmutableList<Planet>
    )

}

object PlanetListViewModelProviderFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlanetListViewModel(NetworkPlanetRepository) as T
    }
}