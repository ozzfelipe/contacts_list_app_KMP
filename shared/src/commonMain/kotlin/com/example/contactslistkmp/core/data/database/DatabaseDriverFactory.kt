package com.example.contactslistkmp.core.data.database

import io.realm.kotlin.Realm

object DatabaseDriverFactory {
    fun create(): Realm {
        return RealmDatabase.openRealm()
    }
}