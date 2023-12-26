package com.example.contactslistkmp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.contactslistkmp.contacts.presentation.ContactListViewModel
import com.example.contactslistkmp.contacts.presentation.components.ContactListScreen
import com.example.contactslistkmp.core.presentation.ContactsTheme
import com.example.contactslistkmp.core.presentation.ImagePicker
import com.example.contactslistkmp.di.AppModule

@Composable
fun App(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    appModule: AppModule,
    imagePicker: ImagePicker,
) {
    ContactsTheme(
        darkTheme,
        dynamicColor,
    ) {
        Surface(
            Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val viewModel = remember { ContactListViewModel(appModule.contactDatasource) }
            val state by viewModel.state.collectAsState()
            ContactListScreen(
                state = state,
                newContact = viewModel.newContact,
                onEvent = viewModel::onEvent,
                imagePicker = imagePicker
            )
        }
    }
}