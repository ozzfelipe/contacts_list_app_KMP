package com.example.contactslistkmp.contacts.data.entities

import com.example.contactslistkmp.contacts.domain.entities.Contact
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ContactDb() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var phone: String = ""
    var photoBytes: ByteArray? = null
    constructor(
        firstName: String = "",
        lastName: String = "",
        email: String = "",
        phone: String = "",
        photoBytes: ByteArray? = null,
    ) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.phone = phone
        this.photoBytes = photoBytes
    }


    fun toContact() : Contact {
        return Contact(
            id = _id.toString(),
            firstName,
            lastName,
            email,
            phone,
            photoBytes
        )
    }
}