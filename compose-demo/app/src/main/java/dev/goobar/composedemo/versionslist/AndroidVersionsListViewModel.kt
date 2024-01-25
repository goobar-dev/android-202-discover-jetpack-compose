package dev.goobar.composedemo.versionslist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.data.AndroidVersionsRepository

class AndroidVersionsListViewModel : ViewModel() {
    val versionsListState: MutableState<List<AndroidVersionInfo>> =
        mutableStateOf(AndroidVersionsRepository.data)
}