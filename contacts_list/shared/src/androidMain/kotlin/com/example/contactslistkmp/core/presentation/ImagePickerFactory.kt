package com.example.contactslistkmp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.activity.ComponentActivity

actual class ImagePickerFactory {
    @Composable
    actual fun createPicker(): ImagePicker {
        val activity = LocalContext.current as ComponentActivity
        return  remember(activity) {
            ImagePicker(activity)
        }
    }


}