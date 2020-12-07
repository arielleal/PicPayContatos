package com.picpay.desafio.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactsViewModel(var userService: UserService): ViewModel() {

    var users = MutableLiveData<List<User>>()
    var errorMessage = MutableLiveData<String>()

    fun loadUsers() {

        userService.loadUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val message = t.message
                    errorMessage.postValue(message)
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                   if (response.body() != null){
                       users.postValue(response.body())
                   }
                }
            })
    }



}