package com.example.fon_classroommanagment_frontend.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//private val DarkColorPaletteDynamic = dynamicDarkColorScheme(
//
//)
//
//private val LightColorPaletteDynamic = dynamicLightColorScheme(
//
//)
private val DarkColorPalette = darkColorScheme(
primary = Blue80,
    onPrimary = Blue20,
    primaryContainer = Blue30,
    onPrimaryContainer = Blue90,
    inversePrimary = Blue40,
    secondary = DarkBlue80,
    onSecondary = DarkBlue20,
    secondaryContainer = DarkBlue30,
    onSecondaryContainer = DarkBlue90,
    error = Red80,
    errorContainer = Red30,
    onErrorContainer = Red90,
    onError = Red20,
    background = Gray10,
    onBackground = Gray90,
    surface = BlueGray30,
    onSurface = BlueGray80,
    inverseOnSurface = Gray90,
    inverseSurface = Gray10,
    surfaceVariant = BlueGray30,
    onSurfaceVariant = BlueGray80,
    outline = BlueGray80


)
private val LightColorPallete = lightColorScheme(
//    primary = Blue40,
//    onPrimary = Color.White,
//    primaryContainer = Blue90,
//    onPrimaryContainer = Blue10,
//    inversePrimary = Blue80,
//    secondary = DarkBlue40,
//    onSecondary = Color.White,
//    secondaryContainer = DarkBlue90,
//    onSecondaryContainer = DarkBlue10,
//    error = Red40,
//    errorContainer = Red90,
//    onErrorContainer = Red10,
//    onError = Color.White,
//    background = Gray99,
//    onBackground = Gray10,
//    surface = BlueGray90,
//    onSurface = BlueGray30,
//    inverseOnSurface = Gray10,
//    inverseSurface = Gray90,
//    surfaceVariant = BlueGray30,
//    onSurfaceVariant = BlueGray80,
//    outline = BlueGray80

    primary = Blue80,
    onPrimary = Blue20,
    primaryContainer = Blue30,
    onPrimaryContainer = Blue90,
    inversePrimary = Blue40,
    secondary = DarkBlue50,
    onSecondary = DarkBlue20,
    secondaryContainer = DarkBlue30,
    onSecondaryContainer = DarkBlue90,
    error = Red80,
    errorContainer = Red30,
    onErrorContainer = Red90,
    onError = Red20,
    background = Gray10,
    onBackground = Gray90,
    surface = BlueGray30,
    onSurface = BlueGray80,
    inverseOnSurface = Gray90,
    inverseSurface = Gray10,
    surfaceVariant = BlueGray30,
    onSurfaceVariant = BlueGray80,
    outline = BlueGray80
)


@Composable
fun FON_ClassroomManagment_FrontendTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

   var colors= DarkColorPalette


    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}