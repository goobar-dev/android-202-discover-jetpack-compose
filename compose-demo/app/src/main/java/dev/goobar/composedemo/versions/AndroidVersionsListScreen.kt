@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.composedemo.versions

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.composedemo.R
import dev.goobar.composedemo.data.AndroidVersionInfo

@Composable
internal fun AndroidVersionsListScreen(viewModel: AndroidVersionsListViewModel = viewModel(), onClick: (AndroidVersionInfo) -> Unit) {
    val versionsListState by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hello Jetpack Compose") },
                actions = {
                    IconButton(onClick = viewModel::onSortClicked) {
                        Icon(painter = painterResource(id = R.drawable.ic_sort), contentDescription = "Sort")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.testTag("Versions List")
            ) {
               itemsIndexed(
                    items = versionsListState.versionsList,
                    key = { index, viewItem -> "$index ${viewItem.info.apiVersion}" }
                ) { index, viewItem ->
                    AndroidVersionInfoCard(viewItem, onClick)
                }
            }
        }
    }
}

@Composable
private fun AndroidVersionInfoCard(
    viewItem: AndroidVersionsListViewModel.State.AndroidVersionViewItem,
    onClick: (AndroidVersionInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .clickable { onClick(viewItem.info) }
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = "Android icon"
            )
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text(text = viewItem.title, style = MaterialTheme.typography.headlineMedium)
                Text(text = viewItem.subtitle)
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = viewItem.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}