package com.example.contactslistkmp.contacts.data.datasource

import com.example.contactslistkmp.contacts.data.entities.ContactDb
import com.example.contactslistkmp.contacts.domain.datasource.ContactDatasource
import com.example.contactslistkmp.contacts.domain.entities.Contact
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class ContactDatasourceImpl(private val realm: Realm) : ContactDatasource {
    override fun getContacts(): Flow<List<Contact>> {
        val results = realm.query<ContactDb>().find().asFlow()

        return results.map { realmResults ->
            realmResults.list.map { contactDb ->
                contactDb.toContact()
            }
        }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        val results =
            realm.query<ContactDb>().sort("_id", Sort.DESCENDING).limit(amount).find().asFlow()

        return results.map { realmResults ->
            realmResults.list.map { contactDb ->
                contactDb.toContact()
            }
        }
    }

    override suspend fun insertContacts(contact: Contact) {
        realm.write {

            val savedContact =
                realm.query<ContactDb>("_id == $0", contact.id?.mapToObjectId()).find()
                    .firstOrNull()

            if (savedContact != null) {
                val contactToUpdate =  findLatest(savedContact)!!

                contactToUpdate.apply {
                    firstName = contact.firstName
                    lastName = contact.lastName
                    phone = contact.phone
                    email = contact.email
                    photoBytes = contact.photoBytes

                }
            } else {
                val contactDb = ContactDb().apply {
                    firstName = contact.firstName
                    lastName = contact.lastName
                    phone = contact.phone
                    email = contact.email
                    photoBytes = contact.photoBytes

                }

                copyToRealm(contactDb)
            }

        }

    }

    override suspend fun deleteContact(id: String) = try {

        val contact = realm.query<ContactDb>("_id == $0", id.mapToObjectId()).find().firstOrNull()

        realm.write {
            if (contact != null) {
                findLatest(contact)?.also { delete(it) }
            }
        }
    } finally {

    }
}

private fun String.mapToObjectId(): ObjectId? {
    return try {
        BsonObjectId(replace(Regex("BsonObjectId|\\(|\\)"), ""));

    } catch (e: Error) {
        null
    }
}