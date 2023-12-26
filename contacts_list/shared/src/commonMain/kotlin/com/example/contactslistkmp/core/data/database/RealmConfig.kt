package com.example.contactslistkmp.core.data.database

import com.example.contactslistkmp.contacts.data.entities.ContactDb
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object RealmDatabase {
    private val CONFIG = RealmConfiguration.create(schema = setOf(ContactDb::class))
    fun openRealm() : Realm  {
        return Realm.open(CONFIG)
    }

}