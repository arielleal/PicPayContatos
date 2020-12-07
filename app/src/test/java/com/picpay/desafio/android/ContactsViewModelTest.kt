package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.service.UserService
import com.picpay.desafio.android.viewModel.ContactsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.mock.Calls

class ContactsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadUsersSuccess(){

        val vmResponse = "[{\"img\":\"https://image.shutterstock.com/image-photo/jay-z-16th-annual-gq-600w-91257293.jpg\", \"name\": \"Jay Z\", \"id\": \"32645698\", \"username\": \"Jay\"}]"
        val listType = object : TypeToken<ArrayList<User>>() {}.type
        val userList: List<User> = Gson().fromJson(vmResponse, listType)
        val response = Response.success(userList)

        val serviceMock = Mockito.mock(UserService::class.java)
        Mockito.`when`(serviceMock.loadUsers())
            .thenReturn(Calls.response(response))

        val listNewsViewModel = ContactsViewModel(serviceMock)
        listNewsViewModel.loadUsers()
        Mockito.verify(serviceMock).loadUsers()
    }

    @Test
    fun loadUsersError(){

        val serviceMock = Mockito.mock(UserService::class.java)
        Mockito.`when`(serviceMock.loadUsers())
            .thenReturn(Calls.failure(Exception("error")))

        val listNewsViewModel = ContactsViewModel(serviceMock)
        listNewsViewModel.loadUsers()
        Mockito.verify(serviceMock).loadUsers()

    }
}