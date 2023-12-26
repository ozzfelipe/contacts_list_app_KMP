package com.example.contactslistkmp.ui.theme

import androidx.compose.ui.graphics.Color

interface AppColorsInterface {
    val primary: Color
    val secondary: Color
    val tertiary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color
    val onTertiary: Color
    val onTertiaryContainer: Color
    val tertiaryContainer: Color
    val background: Color
    val onBackground: Color
    val surface: Color
    val onSurface: Color
    val surfaceVariant: Color
    val onSurfaceVariant: Color
    val error: Color
    val onError: Color
    val errorContainer: Color
    val onErrorContainer: Color
    val outline: Color
}

object LightColors : AppColorsInterface {
    override val primary: Color = GreenPrimaryLight
    override val secondary: Color = GreenSecondaryLight
    override val tertiary: Color = GreenTertiaryLight
    override val onPrimary: Color = OnGreenLight
    override val primaryContainer: Color = GreenContainerLight
    override val onPrimaryContainer: Color = OnGreenContainerLight
    override val onSecondary: Color = OnGreenSecondaryLight
    override val secondaryContainer: Color = GreenSecondaryContainerLight
    override val onSecondaryContainer: Color = OnGreenSecondaryContainerLight
    override val onTertiary: Color = OnGreenTertiaryLight
    override val onTertiaryContainer: Color = OnGreenTertiaryContainerLight
    override val tertiaryContainer: Color = GreenTertiaryContainerLight
    override val background: Color = BackgroundLight
    override val onBackground: Color = OnBackgroundLight
    override val surface: Color = SurfaceLight
    override val onSurface: Color = OnSurfaceLight
    override val surfaceVariant: Color = SurfaceVariantLight
    override val onSurfaceVariant: Color = OnSurfaceVariantLight
    override val error: Color = ErrorLight
    override val onError: Color = OnErrorLight
    override val errorContainer: Color = ErrorContainerLight
    override val onErrorContainer: Color = OnErrorContainerLight
    override val outline: Color = OutlineLight
}

object DarkColors : AppColorsInterface {
    override val primary: Color = GreenPrimaryDark
    override val secondary: Color = GreenSecondaryDark
    override val tertiary: Color = GreenTertiaryDark
    override val onPrimary: Color = OnGreenDark
    override val primaryContainer: Color = GreenContainerDark
    override val onPrimaryContainer: Color = OnGreenContainerDark
    override val onSecondary: Color = OnGreenSecondaryDark
    override val secondaryContainer: Color = GreenSecondaryContainerDark
    override val onSecondaryContainer: Color = OnGreenSecondaryContainerDark
    override val onTertiary: Color = OnGreenTertiaryDark
    override val onTertiaryContainer: Color = OnGreenTertiaryContainerDark
    override val tertiaryContainer: Color = GreenTertiaryContainerDark
    override val background: Color = BackgroundDark
    override val onBackground: Color = OnBackgroundDark
    override val surface: Color = SurfaceDark
    override val onSurface: Color = OnSurfaceDark
    override val surfaceVariant: Color = SurfaceVariantDark
    override val onSurfaceVariant: Color = OnSurfaceVariantDark
    override val error: Color = ErrorDark
    override val onError: Color = OnErrorDark
    override val errorContainer: Color = ErrorContainerDark
    override val onErrorContainer: Color = OnErrorContainerDark
    override val outline: Color = OutlineDark
}

// Light
val GreenPrimaryLight = Color(0xff006e26)
val OnGreenLight = Color(0xffffffff)
val GreenContainerLight = Color(0xff6cff82)
val OnGreenContainerLight = Color(0xff002106)

val GreenSecondaryLight = Color(0xff526350)
val OnGreenSecondaryLight = OnGreenLight
val GreenSecondaryContainerLight = Color(0xffd4e8d0)
val OnGreenSecondaryContainerLight = Color(0xff101f10)

val GreenTertiaryLight = Color(0xff39656b)
val OnGreenTertiaryLight = OnGreenLight
val GreenTertiaryContainerLight = Color(0xffbcebf2)
val OnGreenTertiaryContainerLight = Color(0xff001f23)

val ErrorLight = Color(0xffba1a1a)
val OnErrorLight = Color(0xffffffff)
val ErrorContainerLight = Color(0xffffdad6)
val OnErrorContainerLight = Color(0xff410002)

val BackgroundLight = Color(0xfffcfdf7)
val OnBackgroundLight = Color(0xff1a1c19)
val SurfaceLight = BackgroundLight
val OnSurfaceLight = OnBackgroundLight
val SurfaceVariantLight = Color(0xffdee5d9)
val OnSurfaceVariantLight = Color(0xff424940)

val OutlineLight = Color(0xff72796f)

// DARK
val GreenPrimaryDark = Color(0xff00e559)
val OnGreenDark = Color(0xff003910)
val GreenContainerDark = Color(0xff00531b)
val OnGreenContainerDark = Color(0xff6cff82)

val GreenSecondaryDark = Color(0xffb9ccb5)
val OnGreenSecondaryDark = OnGreenDark
val GreenSecondaryContainerDark = Color(0xff3a4b39)
val OnGreenSecondaryContainerDark = Color(0xffd4e8d0)

val GreenTertiaryDark = Color(0xffa1ced5)
val OnGreenTertiaryDark = Color(0xff00363c)
val GreenTertiaryContainerDark = Color(0xff1f4d53)
val OnGreenTertiaryContainerDark = Color(0xffbcebf2)

val ErrorDark = Color(0xffffb4ab)
val OnErrorDark = Color(0xff690005)
val ErrorContainerDark = Color(0xff93000a)
val OnErrorContainerDark = Color(0xffffdad6)

val BackgroundDark = Color(0xff1a1c19)
val OnBackgroundDark = Color(0xffe2e3dd)
val SurfaceDark = BackgroundDark
val OnSurfaceDark = OnBackgroundDark
val SurfaceVariantDark = Color(0xff424940)
val OnSurfaceVariantDark = Color(0xffc2c9bd)

val OutlineDark = Color(0xff72796f)
