package com.example.redioteka.repository

import com.example.redioteka.ServiceApi
import com.example.redioteka.models.Actor
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ActorRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://redioteka.com/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
    private val api = retrofit.create(ServiceApi::class.java)

    suspend fun getActor(actorId: String): Actor = withContext(Dispatchers.IO) {
        return@withContext api.getActor(actorId)
    }
}