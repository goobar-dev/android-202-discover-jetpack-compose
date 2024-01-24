@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.composedemo.versiondetails

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.composedemo.R
import dev.goobar.composedemo.data.AndroidVersionInfo

@Composable
fun AndroidVersionDetailsAppBar(info: AndroidVersionInfo, onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = info.publicName,
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
fun AndroidVersionDetailsScreen(info: AndroidVersionInfo, onBack: () -> Unit) {
    BackHandler(enabled = true, onBack = onBack)
    Column(modifier = Modifier.padding(20.dp)) {
        Text(text = info.publicName, style = MaterialTheme.typography.displayMedium)
        Text(text = "${info.codename} - API ${info.apiVersion}", style = MaterialTheme.typography.headlineSmall)
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = info.details,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}