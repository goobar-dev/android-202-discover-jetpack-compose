@file:OptIn(ExperimentalMaterial3Api::class)

package dev.goobar.composedemo.versionslist

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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.goobar.composedemo.R
import dev.goobar.composedemo.data.AndroidVersionInfo
import dev.goobar.composedemo.data.AndroidVersionsRepository

@Composable
fun AndroidVersionListAppBar() {
    TopAppBar(title = { Text("Hello Jetpack Compose") })
}

@Composable
fun AndroidVersionsListScreen(onClick: (AndroidVersionInfo) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = AndroidVersionsRepository.data,
            key = { info -> info.apiVersion }) { info ->
            AndroidVersionInfoCard(info, onClick)
        }
    }
}

@Composable
private fun AndroidVersionInfoCard(
    info: AndroidVersionInfo,
    onClick: (AndroidVersionInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .clickable { onClick(info) }
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
                Text(text = info.publicName, style = MaterialTheme.typography.headlineMedium)
                Text(text = "${info.codename} - API ${info.apiVersion}")
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = info.details,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
