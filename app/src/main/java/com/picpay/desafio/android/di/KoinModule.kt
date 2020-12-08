package com.picpay.desafio.android.di

import com.picpay.desafio.android.business.ContactsBusiness
import com.picpay.desafio.android.business.ContactsBusinessImplementation
import com.picpay.desafio.android.repository.ContactsRepository
import com.picpay.desafio.android.repository.ContactsRepositoryImplementation
import com.picpay.desafio.android.viewModel.ContactsViewModel
import com.picpay.desafio.android.service.ServiceConfiguration
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {

    viewModel {
        ContactsViewModel(get(named("teste")), Dispatchers.Main)
    }

    factory (named("teste")) {
        ContactsBusinessImplementation(get())
    } bind ContactsBusiness::class

    factory {
        ContactsRepositoryImplementation(get())
    } bind ContactsRepository::class

    factory {
        ServiceConfiguration()
    }
}