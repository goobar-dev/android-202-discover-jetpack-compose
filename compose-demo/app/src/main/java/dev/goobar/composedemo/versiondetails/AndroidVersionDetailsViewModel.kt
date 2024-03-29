package dev.goobar.composedemo.versiondetails

import androidx.lifecycle.ViewModel
import dev.goobar.composedemo.data.AndroidVersionInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AndroidVersionDetailsViewModel(info: AndroidVersionInfo) : ViewModel() {

    val state: StateFlow<State> = MutableStateFlow(
        State(
            title = info.publicName,
            subtitle = "${info.codename} - API ${info.apiVersion}",
            description = info.details
        )
    )

    data class State(
        val title: String,
        val subtitle: String,
        val description: String
    )
}