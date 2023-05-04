package dev.goobar.composedemo.versions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.data.AndroidVersionsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.plus

enum class Sort {
    ASCENDING, DESCENDING
}

class AndroidVersionsListViewModel : ViewModel() {

    private var sort = Sort.ASCENDING

    private val _state = flow {
        val items = AndroidVersionsRepository.data
        val itemsToReturn = mutableListOf<AndroidVersionInfo>()

        items.forEach { info ->
            delay(4000)
            itemsToReturn.add(info)
            emit(
                State(
                    itemsToReturn.map { info ->
                        State.AndroidVersionViewItem(
                            title = info.publicName,
                            subtitle = "${info.codename} - API ${info.apiVersion}",
                            description = info.details,
                            info = info
                        )
                    }
                )
            )
        }
    }
    val state = _state.stateIn(viewModelScope + Dispatchers.Main.immediate, SharingStarted.WhileSubscribed(3_000), State(
        emptyList()))

    fun onSortClicked() {
//        sort = when (sort) {
//            Sort.ASCENDING -> Sort.DESCENDING
//            Sort.DESCENDING -> Sort.ASCENDING
//        }
//        _state.update { generateState() }
    }

    private fun generateState() = State(
        versionsList = when (sort) {
            Sort.ASCENDING -> AndroidVersionsRepository.data.sortedBy { it.apiVersion }
            Sort.DESCENDING -> AndroidVersionsRepository.data.sortedByDescending { it.apiVersion }
        }.map { info ->
            State.AndroidVersionViewItem(
                title = info.publicName,
                subtitle = "${info.codename} - API ${info.apiVersion}",
                description = info.details,
                info = info
            )
        }
    )

    data class State(val versionsList: List<AndroidVersionViewItem>) {
        data class AndroidVersionViewItem(
            val title: String,
            val subtitle: String,
            val description: String,
            val info: AndroidVersionInfo
        )
    }
}