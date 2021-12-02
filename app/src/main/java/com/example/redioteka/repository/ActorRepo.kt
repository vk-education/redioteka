package com.example.redioteka.repository

import android.content.Context
import com.example.redioteka.models.Actor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActorRepo(context: Context) {
    private val api = createApi(context)

    suspend fun getActor(actorId: String): Actor = withContext(Dispatchers.IO) {
        return@withContext api.getActor(actorId)
    }
}
