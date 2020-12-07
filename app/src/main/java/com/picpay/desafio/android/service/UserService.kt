package com.picpay.desafio.android.service

import com.picpay.desafio.android.model.User
import retrofit2.Call
import retrofit2.Retrofit

class UserService {
    private val retrofitClient: Retrofit = ServiceConfiguration().retrofit()
    private val endpoint = retrofitClient.create(PicPayService::class.java)

     fun loadUsers(): Call<List<User>> {
        return endpoint.getUsers()
    }

}