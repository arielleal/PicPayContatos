package com.picpay.desafio.android.business

import com.picpay.desafio.android.repository.ContactsRepository

class ContactsBusinessImplementation(private val repository: ContactsRepository): ContactsBusiness {

    override suspend fun getUsers() = repository.getUser()
}