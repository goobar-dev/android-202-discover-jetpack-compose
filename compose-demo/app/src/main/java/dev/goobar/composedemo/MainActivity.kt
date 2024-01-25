@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package dev.goobar.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import dev.goobar.composedemo.ui.config.ScreenConfiguration
import dev.goobar.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val windowSizeClass = calculateWindowSizeClass(this)
                    val configuration = LocalConfiguration.current
                    val screenConfiguration = ScreenConfiguration.from(windowSizeClass, configuration)

                    ComposeDemoNavigationGraph(screenConfiguration)
                }
            }
        }
    }
}
