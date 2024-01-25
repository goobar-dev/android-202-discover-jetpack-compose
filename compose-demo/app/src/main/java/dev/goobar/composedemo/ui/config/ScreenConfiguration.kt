package dev.goobar.composedemo.ui.config

import android.content.res.Configuration
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass

data class ScreenConfiguration(
    val isCompact: Boolean,
    val orientation: Orientation
) {
    enum class Orientation {
        UNDEFINED,
        VERTICAL,
        HORIZONTAL
    }

    companion object {
        fun from(windowSizeClass: WindowSizeClass, configuration: Configuration): ScreenConfiguration {
            val isCompact =
                windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

            val orientation = Orientation.entries[configuration.orientation]

            return ScreenConfiguration(isCompact, orientation)
        }
    }
}