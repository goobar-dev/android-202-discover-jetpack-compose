@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.composedemo.versiondetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.goobar.composedemo.R

@Composable
fun AndroidVersionDetailsAppBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.padding(start = 20.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back button"
                )
            }
        }
    )
}

@Composable
fun AndroidVersionDetailsScreen(
    viewModel: AndroidVersionDetailsViewModel,
    onBack: () -> Unit
) {
    BackHandler(enabled = true, onBack = onBack)
    val layoutDirection = LocalLayoutDirection.current
    val state: AndroidVersionDetailsViewModel.State by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AndroidVersionDetailsAppBar(state.title, onBack)
        }
    ) {
        Column(
            modifier = Modifier.padding(
                start = 20.dp + it.calculateStartPadding(layoutDirection),
                top = 20.dp + it.calculateTopPadding(),
                end = 20.dp + it.calculateEndPadding(layoutDirection),
                bottom = 20.dp + it.calculateBottomPadding()
            )
        ) {
            Text(text = state.title, style = MaterialTheme.typography.displayMedium)
            Text(text = state.subtitle, style = MaterialTheme.typography.headlineSmall)
            Text(
                modifier = Modifier.padding(top = 20.dp),
                text = state.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}