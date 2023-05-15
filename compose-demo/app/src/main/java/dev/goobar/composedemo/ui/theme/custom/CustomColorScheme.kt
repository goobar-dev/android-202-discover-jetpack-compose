package dev.goobar.composedemo.ui.theme.custom

import androidx.compose.ui.graphics.Color
import dev.goobar.composedemo.ui.theme.Blue

data class CustomColorScheme(
    val primary: Color
)

fun defaultCustomColorScheme() = CustomColorScheme(
    primary = Blue
)
