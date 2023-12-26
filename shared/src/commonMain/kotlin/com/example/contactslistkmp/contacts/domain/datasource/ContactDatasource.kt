package com.example.contactslistkmp.contacts.domain.datasource

import com.example.contactslistkmp.contacts.domain.entities.Contact
import kotlinx.coroutines.flow.Flow

interface ContactDatasource {
    fun getContacts(): Flow<List<Contact>>
    fun getRecentContacts(amount: Int): Flow<List<Contact>>
    suspend fun insertContacts(contact: Contact)
    suspend fun deleteContact(id: String)
}