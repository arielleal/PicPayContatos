package com.picpay.desafio.android.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceConfiguration {

    private val url = "http://careers.picpay.com/tests/mobdev/"

    private val retrofit: Retrofit by lazy {
         Retrofit.Builder()
             .baseUrl(url)
             .client(okHttp)
             .addConverterFactory(GsonConverterFactory.create(gson))
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build()
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val gson: Gson by lazy {
        GsonBuilder()
        .create()
    }

    fun getInstance(): PicPayService = retrofit.create(PicPayService::class.java)
}