package com.example.redioteka.repository

import com.example.redioteka.ServiceApi
import com.example.redioteka.models.User
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://redioteka.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val api = retrofit.create(ServiceApi::class.java)

    suspend fun getUser(userId: String): User = withContext(Dispatchers.IO) {
        return@withContext api.getUser(userId)
    }
}
