package com.osequeiros.laptoplist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = MountainMeadow,
    primaryVariant = WarmBlack,
    secondary = MediumTurquoise,
    background = DarkJungleGreen,
    onPrimary = Gainsboro,
    onBackground = Gainsboro,
)

private val LightColorPalette = lightColors(
    primary = MountainMeadow,
    primaryVariant = Purple700,
    secondary = BlueSapphire,
    onPrimary = Gainsboro,
    background = GhostWhite,
    onBackground = RichBlack,

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

@Composable
fun LaptopsListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}