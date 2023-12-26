package com.example.contactslistkmp

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import com.example.contactslistkmp.core.presentation.ImagePicker
import com.example.contactslistkmp.core.presentation.ImagePickerFactory
import com.example.contactslistkmp.di.AppModule
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

fun MainViewController() = ComposeUIViewController {
    val isDarkTheme =
        UIScreen.mainScreen.traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    App(
        isDarkTheme,
        false,
        appModule = AppModule,
        imagePicker = ImagePickerFactory(LocalUIViewController.current).createPicker()
    )
}