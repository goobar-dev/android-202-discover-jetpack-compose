package dev.goobar.composedemo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.data.AndroidVersionsRepository
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsAppBar
import dev.goobar.composedemo.versiondetails.AndroidVersionDetailsScreen
import dev.goobar.composedemo.versionslist.AndroidVersionListAppBar
import dev.goobar.composedemo.versionslist.AndroidVersionsListScreen

@Composable
fun MainScreen() {
    var selectedItem by rememberSaveable { mutableStateOf<AndroidVersionInfo?>(null) }

    Scaffold(
        topBar = {
            when (val currentItem = selectedItem) {
                null -> {
                    AndroidVersionListAppBar()
                }
                else -> {
                    AndroidVersionDetailsAppBar(info = currentItem) {
                        selectedItem = null
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val currentItem = selectedItem) {
                null -> AndroidVersionsListScreen(AndroidVersionsRepository.data) { clickedInfo ->
                    selectedItem = clickedInfo
                }
                else -> AndroidVersionDetailsScreen(currentItem) {
                    selectedItem = null
                }
            }
        }
    }
}