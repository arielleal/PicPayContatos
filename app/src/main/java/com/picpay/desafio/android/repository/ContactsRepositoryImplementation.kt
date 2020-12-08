package com.picpay.desafio.android.repository

import android.provider.Settings.Global.getString
import android.util.Log
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.service.ServiceConfiguration
import org.json.JSONObject

class ContactsRepositoryImplementation(service: ServiceConfiguration) : ContactsRepository {

    private val retrofit = service.getInstance()

    override suspend fun getUser(): List<User> {
        return try {
            val call = retrofit.getUsers()
            if (call.isSuccessful) {
                call.body()!!
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.d("ExceptionChamada: ", e.message)
            emptyList()
        }
    }
}