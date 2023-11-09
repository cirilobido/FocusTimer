package com.cirilobido.focustimer.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/*
Screen Sizes
*/
internal const val COMPACT_SCREEN_WIDTH = 600
internal const val MEDIUM_SCREEN_WIDTH = 839

private val LocalDimens = staticCompositionLocalOf { DefaultsDimens }

private val DarkColorScheme = darkColorScheme(
    primary = primaryWhite,
    onPrimary = primaryWhite,
    secondary = gray,
    onSecondary = gray,
    tertiary = lightGray,
    surface = primaryBlack,
    background = primaryBlack,
)

private val LightColorScheme = lightColorScheme(
    primary = primaryBlack,
    onPrimary = primaryBlack,
    secondary = gray,
    onSecondary = gray,
    tertiary = lightGray,
    surface = primaryWhite,
    background = primaryWhite,
)

@Composable
private fun ProvideDimens(
    dimensions: Dimens,
    content: @Composable () -> Unit,
) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalDimens provides dimensionSet, content = content)
}

@Composable
fun FocusTimerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    /**
     * Screen Sizes
     * You can also use androidx.compose.material3.windowsizeclass dependency
     * instead of adding the values manually
     */
    val currentWidth = LocalConfiguration.current.screenWidthDp
    val dimensions =
        if (currentWidth in COMPACT_SCREEN_WIDTH..MEDIUM_SCREEN_WIDTH)
            TabletDimens
        else
            DefaultsDimens

    ProvideDimens(dimensions = dimensions) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

/**
 * Two Ways to get the dimens
 * YOUR_THEME.dimens.YOUR_DIMENSION
 * CurrentDimens.YOUR_DIMENSION
 * val CurrentDimens: Dimens
 * @Composable
 * @ReadOnlyComposable
 * get() = LocalDimens.current*/

object FocusTimerTheme {
    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}