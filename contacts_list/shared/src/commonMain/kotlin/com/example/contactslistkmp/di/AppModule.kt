package com.example.contactslistkmp.di

import com.example.contactslistkmp.contacts.data.datasource.ContactDatasourceImpl
import com.example.contactslistkmp.contacts.domain.datasource.ContactDatasource
import com.example.contactslistkmp.core.data.database.DatabaseDriverFactory

object AppModule {
    val contactDatasource: ContactDatasource = ContactDatasourceImpl(DatabaseDriverFactory.create())
}