package com.example.contactslistkmp.contacts.domain.entities

data class Contact(
    val id: String?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val photoBytes: ByteArray? = null
) {

    companion object Factory {
        fun empty(): Contact = Contact(
            id = null,
            firstName = "",
            lastName = "",
            email = "",
            phone = "",
        )
    }
}

