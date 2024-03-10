package com.projects.movierater.data.network.interceptor

import com.projects.movierater.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request()

        val newUrl =
            currentRequest.url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

        val newRequest = currentRequest.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)

    }
}
