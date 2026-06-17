package com.korommipress.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val KorommiPurple = Color(0xFF8A5AE6)
private val KorommiPurpleContainer = Color(0xFF5E3BD6)
private val KorommiBackground = Color(0xFF0A0A0D) // near-black
private val KorommiSurface = Color(0xFF0F0F13)
private val KorommiOnPrimary = Color.White
private val KorommiOnBackground = Color(0xFFEFEFF3)

private val DarkColorScheme = darkColorScheme(
    primary = KorommiPurple,
    onPrimary = KorommiOnPrimary,
    secondary = KorommiPurpleContainer,
    background = KorommiBackground,
    surface = KorommiSurface,
    onBackground = KorommiOnBackground,
    error = Color(0xFFFF6B6B),
    onError = Color.White
)

@Composable
fun KorommiTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // We force dark theme for the app style; respect the flag for previews if needed.
    val colors = DarkColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = Typography(), // provide your typography if available
        shapes = Shapes(
            small = RoundedCornerShape(12.dp),
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(20.dp)
        ),
        content = content
    )
}
