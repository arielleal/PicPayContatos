package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRepositoryImplementation
import com.picpay.desafio.android.viewModel.ContactsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ContactsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    companion object{
        @BeforeClass
        @JvmStatic
        fun setUp() {
        MockitoAnnotations.initMocks(this)
    }}



    private val vmResponse = "[{\"img\":\"https://image.shutterstock.com/image-photo/jay-z-16th-annual-gq-600w-91257293.jpg\", \"name\": \"Jay Z\", \"id\": \"32645698\", \"username\": \"Jay\"}]"
    private val listType = object : TypeToken<ArrayList<User>>() {}.type
    private val userList: List<User> = Gson().fromJson(vmResponse, listType)

    @Test
    fun loadUsersSuccess() = runBlocking {
       // val response = Response.success(userList)

        val serviceMock = Mockito.mock(ContactsRepositoryImplementation::class.java)
        Mockito.`when`(serviceMock.getUser())
            .thenReturn(userList)

        val listNewsViewModel = ContactsViewModel(serviceMock, Dispatchers.Main)
        listNewsViewModel.loadUsers()
        Mockito.verify(serviceMock).getUser()
    }

    @Test
    suspend fun loadUsersError() {
        val serviceMock = Mockito.mock(ContactsRepositoryImplementation::class.java)
        Mockito.`when`(serviceMock.getUser())
            .thenReturn(userList)

        val listNewsViewModel = ContactsViewModel(serviceMock, Dispatchers.Main)
        listNewsViewModel.loadUsers()
        Mockito.verify(serviceMock.getUser())

    }
}



/*
private fun Any.thenReturn(response: Response<List<User>>?) {
}
*/
