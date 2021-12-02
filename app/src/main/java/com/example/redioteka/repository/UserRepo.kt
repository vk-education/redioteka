package com.example.redioteka.repository

import android.content.Context
import android.util.Log
import com.example.redioteka.models.User
import com.example.redioteka.models.UserAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepo(context: Context) {
    private val api = createApi(context)

    suspend fun getUser(userId: String): User = withContext(Dispatchers.IO) {
        return@withContext api.getUser(userId)
    }

    suspend fun login(user: UserAuth): Result<User> = withContext(Dispatchers.IO) {
        try {
            val data: User = api.loginUser(user)
            Log.i("QUERY", data.toString())
            return@withContext Result.Success(data)
        } catch (e: Exception) {
            Log.i("ERROR", e.toString())
            return@withContext Result.Fail(e)
        }
    }
}
