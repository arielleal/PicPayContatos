package com.picpay.desafio.android.di

import com.picpay.desafio.android.viewModel.ContactsViewModel
import com.picpay.desafio.android.service.ServiceConfiguration
import com.picpay.desafio.android.service.UserService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    factory {
        UserService()
    }
    viewModel {
       ContactsViewModel(userService = get())
    }
}