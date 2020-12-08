package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService

class ExampleService(
    private val service: PicPayService
) {
    suspend fun example(): List<User> {
      val users = service.getUsers()
        return users.body() ?: emptyList()
    }
}