package dev.goobar.composedemo.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val ComposeDemoShapes = Shapes(
    extraSmall = ShapeDefaults.ExtraSmall,
    small = ShapeDefaults.Small,
    medium = RoundedCornerShape(
        topStart = 4.dp,
        topEnd = 32.dp,
        bottomEnd = 4.dp,
        bottomStart = 4.dp
    ),
    large = ShapeDefaults.Large,
    extraLarge = ShapeDefaults.ExtraLarge
)