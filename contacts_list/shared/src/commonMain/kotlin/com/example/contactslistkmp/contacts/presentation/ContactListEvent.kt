package com.example.contactslistkmp.contacts.presentation

import com.example.contactslistkmp.contacts.domain.entities.Contact

sealed interface ContactListEvent {
    data object OnAddNewContactClick : ContactListEvent
    data object DismissContact : ContactListEvent
    data class OnFirstNameChanged(val value: String) : ContactListEvent
    data class OnLastNameChanged(val value: String) : ContactListEvent
    data class OnEmailChanged(val value: String) : ContactListEvent
    data class OnPhoneChanged(val value: String) : ContactListEvent
    class OnPhotoPicked(val bytes: ByteArray) : ContactListEvent
    data object OnAddPhotoClicked : ContactListEvent
    data object SaveContact : ContactListEvent
    data class SelectContact(val contact: Contact) : ContactListEvent
    data class EditContact(val contact: Contact) : ContactListEvent
    data object DeleteContact : ContactListEvent
}
