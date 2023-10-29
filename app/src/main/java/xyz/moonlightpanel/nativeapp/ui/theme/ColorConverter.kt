package xyz.moonlightpanel.nativeapp.ui.theme

import android.graphics.Color

fun Color.kt(): androidx.compose.ui.graphics.Color {
    return androidx.compose.ui.graphics.Color(red = red(), green = green(), blue = blue(), alpha = alpha())
}