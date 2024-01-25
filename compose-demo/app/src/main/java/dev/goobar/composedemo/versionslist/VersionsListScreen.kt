@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.composedemo.versionslist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.goobar.composedemo.R
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.data.AndroidVersionsRepository
import dev.goobar.composedemo.ui.theme.ComposeDemoTheme
import dev.goobar.composedemo.ui.tooling.StandardPreview

@Composable
fun AndroidVersionListAppBar(onSortClick: () -> Unit) {
    TopAppBar(
        title = { Text("Hello Jetpack Compose") },
        actions = {
            IconButton(onClick = onSortClick) {
                Icon(painter = painterResource(id = R.drawable.ic_sort), contentDescription = "Sort")
            }
        }
    )
}

@Composable
fun AndroidVersionsListScreen(
    viewModel: AndroidVersionsListViewModel = viewModel(),
    onClick: (AndroidVersionInfo) -> Unit) {

    val versionsListState by viewModel.state.collectAsStateWithLifecycle()

    AndroidVersionsListContent(
        viewItems = versionsListState.versionsList,
        onSortClick =  viewModel::onSortClicked,
        onClick = onClick)
}

@Composable
private fun AndroidVersionsListContent(
    viewItems: List<AndroidVersionsListViewModel.State.AndroidVersionViewItem>,
    onSortClick: () -> Unit,
    onClick: (AndroidVersionInfo) -> Unit
) {
    Scaffold(
        topBar = {
            AndroidVersionListAppBar(onSortClick)
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                items = viewItems,
                key = { index, viewItem -> "$index ${viewItem.info.apiVersion}" }
            ) { index, viewItem ->
                AndroidVersionInfoCard(viewItem, onClick)
            }
        }
    }
}

@Composable
private fun AndroidVersionInfoCard(
    viewItem: AndroidVersionsListViewModel.State.AndroidVersionViewItem,
    onClick: (AndroidVersionInfo) -> Unit
) {
    var isDetailExpanded by rememberSaveable(viewItem.info.apiVersion) { mutableStateOf(false) }

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
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = viewItem.title, style = MaterialTheme.typography.headlineMedium)

                    if (viewItem.description.isNotBlank()) {
                        IconButton(onClick = { isDetailExpanded = !isDetailExpanded }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_info),
                                contentDescription = "Info"
                            )
                        }
                    }
                }
                Text(text = viewItem.subtitle)

                AnimatedVisibility(visible = isDetailExpanded) {
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
}

private class AndroidVersionInfoProvider : PreviewParameterProvider<List<AndroidVersionInfo>> {
    override val values: Sequence<List<AndroidVersionInfo>>
        get() = sequenceOf(
            emptyList(),
            AndroidVersionsRepository.data.take(1),
            AndroidVersionsRepository.data.take(2),
            AndroidVersionsRepository.data,
        )

}

@StandardPreview
@Composable
private fun Preview_AndroidVersionsListScreen(
    @PreviewParameter(AndroidVersionInfoProvider::class) infos: List<AndroidVersionInfo>
) {
    ComposeDemoTheme {
        AndroidVersionsListContent(infos.map { it.toViewItem() }, onSortClick = {}, onClick = {})
    }
}
