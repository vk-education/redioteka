package com.example.redioteka.interceptors

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CookieInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = addCookies(chain.request())

        val response = chain.proceed(newRequest)
        if (response.isSuccessful && response.headers(SET_HEADER).isNotEmpty()) {
            saveCookies(response.headers(SET_HEADER))
        }
        return response
    }

    private fun saveCookies(cookies: List<String>) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val savedCookies = prefs.getStringSet(COOKIES, HashSet<String>())!!
        Log.i("COOKIE", cookies.toString())
        savedCookies.addAll(cookies)
        val editor = prefs.edit()
        editor.putStringSet(COOKIES, savedCookies)
            .apply()
    }

    private fun addCookies(request: Request): Request {
        val newRequest = request.newBuilder()
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        val cookies = prefs.getStringSet(COOKIES, HashSet<String>())!!
        for (cookie in cookies) {
            newRequest.addHeader(HEADER, cookie)
        }
        return newRequest.build()
    }

    companion object {
        const val SET_HEADER = "Set-Cookie"
        const val HEADER = "Cookie"
        const val COOKIES = "PREF_COOKIES"
    }
}