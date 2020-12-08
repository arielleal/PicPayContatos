package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User

interface ContactsBusiness {
    suspend fun getUsers(): List<User>
}