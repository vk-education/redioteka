package com.example.redioteka.interceptors


import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CsrfInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (request.method == GET_METHOD) {
            return chain.proceed(request)
        }

        var newRequest = getRequestWithCsrf(request)
        val response = chain.proceed(newRequest)
        if (response.isSuccessful)
            return response

        response.close()
        val csrfRequest = request.newBuilder()
            .get()
            .url(CSRF_URL)
            .build()
        chain.proceed(csrfRequest).close()

        newRequest = getRequestWithCsrf(request)
        return chain.proceed(newRequest)
    }

    private fun getRequestWithCsrf(request: Request): Request {
        val newRequest = request.newBuilder()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val cookies = prefs.getStringSet(COOKIES, HashSet<String>())!!
        for (cookie in cookies) {
            if (cookie.contains(TOKEN)) {
                val index = cookie.indexOf(TOKEN)+ TOKEN.length + 1
                Log.i("TOKEN", cookie.slice(index until index+ TOKEN_LEN))
                newRequest.addHeader(HEADER, cookie.slice(index until index+ TOKEN_LEN))
            }
        }
        return newRequest.build()
    }

    companion object {
        const val TOKEN = "csrf_token"
        const val TOKEN_LEN = 32
        const val GET_METHOD = "GET"
        const val HEADER = "X-CSRF-Token"
        const val CSRF_URL = "https://redioteka.com/api/csrf"
        const val COOKIES = "PREF_COOKIES"
    }
}