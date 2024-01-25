package dev.goobar.composelabs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.goobar.composelabs.network.PlanetDTO
import dev.goobar.composelabs.network.SWAPINetworkClient
import dev.goobar.composelabs.network.toPlanet
import dev.goobar.composelabs.planetslist.PlanetsListScreen
import dev.goobar.composelabs.ui.theme.ComposeLabsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLabsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    var planets by remember { mutableStateOf<List<PlanetDTO>>(emptyList()) }
                    LaunchedEffect(Unit) {
                        launch(Dispatchers.IO) {
                            planets = SWAPINetworkClient.getPlanets().results
                        }
                    }

                    val context = LocalContext.current
                    PlanetsListScreen(planets = planets.map { it.toPlanet() }) { planet ->
                        Toast.makeText(context, planet.name, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
private fun PlanetListItem(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}