package com.picpay.desafio.android.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceConfiguration() {

    private val url = "http://careers.picpay.com/tests/mobdev/"

     fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttp())
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .build()
    }

    private fun okHttp(): OkHttpClient {return OkHttpClient.Builder().build()}

    private fun gson(): Gson {return GsonBuilder().create()}
}