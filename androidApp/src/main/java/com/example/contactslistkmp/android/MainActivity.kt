package com.example.contactslistkmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.contactslistkmp.App
import com.example.contactslistkmp.core.presentation.ImagePicker
import com.example.contactslistkmp.core.presentation.ImagePickerFactory
import com.example.contactslistkmp.di.AppModule

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                appModule = buildAppModule(),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    App(
        appModule = buildAppModule(),
        imagePicker = ImagePickerFactory().createPicker()
    )
}

@Composable
private fun buildAppModule(): AppModule {
    return AppModule
}
