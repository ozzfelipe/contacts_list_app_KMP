package com.example.contactslistkmp.contacts.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.contactslistkmp.contacts.domain.ContactValidator
import com.example.contactslistkmp.contacts.domain.datasource.ContactDatasource
import com.example.contactslistkmp.contacts.domain.entities.Contact
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactListViewModel(
    private val contactDatasource: ContactDatasource
) : ViewModel() {
    private val _state = MutableStateFlow(ContactsListState())
    val state = combine(
        _state,
        contactDatasource.getContacts(),
        contactDatasource.getRecentContacts(20),
    ) { state, contacts, recentContacts ->
        state.copy(
            contacts = contacts,
            recentlyAddedContacts = recentContacts
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), ContactsListState())

    var newContact: Contact? by mutableStateOf(null)
        private set

    fun onEvent(event: ContactListEvent) {
        when (event) {
            ContactListEvent.DeleteContact -> deleteContactHandle()
            ContactListEvent.DismissContact -> dismissContactHandle()
            is ContactListEvent.EditContact -> editContactHandle(event.contact)
            ContactListEvent.OnAddNewContactClick -> onAddNewContactHandle()
            is ContactListEvent.OnEmailChanged -> onUpdateNewContactHandle(event)
            is ContactListEvent.OnFirstNameChanged -> onUpdateNewContactHandle(event)
            is ContactListEvent.OnLastNameChanged -> onUpdateNewContactHandle(event)
            is ContactListEvent.OnPhoneChanged -> onUpdateNewContactHandle(event)
            is ContactListEvent.OnPhotoPicked -> onUpdateNewContactHandle(event)
            ContactListEvent.SaveContact -> onSaveContactHandle()
            is ContactListEvent.SelectContact -> onSelectContactHandle(event.contact)
            else -> Unit
        }
    }

    private fun deleteContactHandle() {
        viewModelScope.launch {
            _state.value.selectedContact?.id?.let { id ->
                _state.update {
                    it.copy(
                        isSelectedContactSheetOpen = false
                    )
                }
                contactDatasource.deleteContact(id)
                closeContactSheet()
            }
        }
    }

    private suspend fun closeContactSheet() {
        _state.update {
            it.copy(
                isAddContactSheetOpen = false,
                isSelectedContactSheetOpen = false,
                selectedContact = null
            )
        }
        delay(300L) // animation delay
        newContact = null
    }

    private fun dismissContactHandle() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isSelectedContactSheetOpen = false,
                    firstNameError = null,
                    lastNameError = null,
                    emailError = null,
                    phoneError = null,
                )
            }
            closeContactSheet()
        }
    }

    private fun editContactHandle(contact: Contact) {
        _state.update {
            it.copy(
                selectedContact = contact,
                isAddContactSheetOpen = true,
                isSelectedContactSheetOpen = false
            )
        }
        newContact = contact
    }

    private fun onAddNewContactHandle() {
        _state.update {
            it.copy(
                isAddContactSheetOpen = true,
            )
        }
        newContact = Contact.empty()
    }

    private fun onUpdateNewContactHandle(event: ContactListEvent) {
        when (event) {
            is ContactListEvent.OnEmailChanged -> {
                newContact = newContact?.copy(email = event.value)
            }

            is ContactListEvent.OnFirstNameChanged -> {
                newContact = newContact?.copy(firstName = event.value)
            }

            is ContactListEvent.OnLastNameChanged -> {
                newContact = newContact?.copy(lastName = event.value)
            }

            is ContactListEvent.OnPhoneChanged -> {
                newContact = newContact?.copy(phone = event.value)
            }

            is ContactListEvent.OnPhotoPicked -> {
                newContact = newContact?.copy(photoBytes = event.bytes)
            }

            else -> {}
        }
    }

    private fun onSaveContactHandle() {
        newContact?.let { contact ->
            var result = ContactValidator.validateContact(contact)

            if (result.hasError()) {
                _state.update {
                    it.copy(
                        firstNameError = result.firstNameError,
                        lastNameError = result.lastNameError,
                        emailError = result.emailError,
                        phoneError = result.phoneError,
                    )
                }
            } else {
                viewModelScope.launch {
                    _state.update {
                        it.copy(
                            firstNameError = null,
                            lastNameError = null,
                            emailError = null,
                            phoneError = null,
                        )
                    }
                    contactDatasource.insertContacts(newContact!!)
                    closeContactSheet()
                    newContact = null
                }
            }
        }
    }

    private fun onSelectContactHandle(contact: Contact) {
        _state.update {
            it.copy(
                selectedContact = contact,
                isSelectedContactSheetOpen = true,
            )
        }
    }
}
