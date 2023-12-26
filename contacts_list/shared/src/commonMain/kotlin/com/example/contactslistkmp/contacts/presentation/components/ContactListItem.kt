package com.example.contactslistkmp.contacts.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.contactslistkmp.contacts.domain.entities.Contact

@Composable
fun ContactListItem(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactPhoto(
            contact,
            Modifier.size(50.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            "${contact.firstName} ${contact.lastName}",
            Modifier.weight(1f)
        )
    }
}