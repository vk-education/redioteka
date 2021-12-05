package com.example.redioteka.repository

import android.content.Context
import com.example.redioteka.ServiceApi
import com.example.redioteka.interceptors.CookieInterceptor
import com.example.redioteka.interceptors.CsrfInterceptor
import com.example.redioteka.interceptors.OriginHeaderInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createApi(context: Context): ServiceApi {
    val client = OkHttpClient.Builder()
        .addInterceptor(OriginHeaderInterceptor())
        .addInterceptor(CsrfInterceptor(context))
        .addInterceptor(CookieInterceptor(context))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://redioteka.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(client)
        .build()

    return retrofit.create(ServiceApi::class.java)
}
