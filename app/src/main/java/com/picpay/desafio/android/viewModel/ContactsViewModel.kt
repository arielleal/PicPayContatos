package com.picpay.desafio.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.business.ContactsBusiness
import com.picpay.desafio.android.model.User
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContactsViewModel(
    var business: ContactsBusiness,
    private val dispatcher: CoroutineContext
): ViewModel() {

    var users = listOf<User>()
    var internalSuccess = MutableLiveData<List<User>>()

    val usersSuccess : MutableLiveData<List<User>>
        get() = internalSuccess

    var errorMessage = MutableLiveData<String>()

     fun loadUsers() {
        viewModelScope.launch (dispatcher) {
            users = business.getUsers()
            callExecuted()
        }
    }

    private fun callExecuted() {
        if (users.isEmpty()) {
            errorMessage.postValue("Internal error")
        } else {
            showList()
        }
    }

    private fun showList() {
        internalSuccess.postValue(users)
    }
}