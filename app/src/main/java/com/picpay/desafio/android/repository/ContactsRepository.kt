package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService

interface ContactsRepository {

    suspend fun getUser() : List<User>
}