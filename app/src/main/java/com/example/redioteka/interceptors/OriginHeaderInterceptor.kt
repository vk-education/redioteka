package com.example.redioteka.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class OriginHeaderInterceptor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()


        val newRequest = request.newBuilder()
            .header("Origin", ORIGIN)
            .build()

        return chain.proceed(newRequest)
    }

    companion object {
        const val ORIGIN = "https://redioteka.com"
    }
}