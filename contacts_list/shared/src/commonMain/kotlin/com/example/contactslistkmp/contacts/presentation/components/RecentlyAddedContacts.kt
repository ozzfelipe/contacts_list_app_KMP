package com.example.contactslistkmp.contacts.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.contactslistkmp.contacts.domain.entities.Contact

@Composable
fun RecentlyAddedContacts(
    contacts: List<Contact>,
    onClick: (Contact) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {

        if (contacts.isNotEmpty()) {
            Text(
                "Recently Added",
                Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
            LazyRow(
                modifier = modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {


                items(
                    contacts
                ) { contact ->
                    ContactPreviewItem(
                        contact,
                        onClick = { onClick(contact) }
                    )
                }
            }
        }
    }


}