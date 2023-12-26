package com.example.contactslistkmp.contacts.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.contactslistkmp.contacts.domain.entities.Contact
import com.example.contactslistkmp.contacts.presentation.ContactListEvent
import com.example.contactslistkmp.contacts.presentation.ContactsListState
import com.example.contactslistkmp.core.presentation.ImagePicker

@Composable
fun ContactListScreen(
    state: ContactsListState,
    newContact: Contact?,
    onEvent: (ContactListEvent) -> Unit,
    imagePicker: ImagePicker,
) {
    imagePicker.registerPicker { imageBytes ->
        onEvent(ContactListEvent.OnPhotoPicked(imageBytes))
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactListEvent.OnAddNewContactClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    Icons.Rounded.PersonAdd,
                    "Add contact"
                )
            }
        }
    ) {
        if (state.contacts.isEmpty()) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Any saved contacts yet, save your contacts to appear here!",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        } else
            LazyColumn(
                Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Text(
                        text = "My contacts (${state.contacts.size})",
                        Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                    )
                }

                items(state.contacts) { contact ->
                    ContactListItem(
                        contact,
                        Modifier.fillMaxWidth()
                            .clickable {
                                onEvent(ContactListEvent.SelectContact(contact))
                            }
                    )
                }
            }

        AddContactSheet(
            state,
            newContact,
            isOpen = state.isAddContactSheetOpen,
            onEvent = { event ->
                if (event is ContactListEvent.OnAddPhotoClicked) {
                    imagePicker.pickImage()
                }
                onEvent(event)
            }
        )
    }
}