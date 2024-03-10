package com.projects.movierater.data.network

import com.projects.movierater.data.BuildConfig
import com.projects.movierater.data.network.adapter.NetworkResponseAdapterFactory
import com.projects.movierater.data.network.interceptor.TokenInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object NetworkProvider {

    private var _mainRetrofit: Retrofit? = null

    val mainRetrofit: Retrofit
        get() {
            return if (_mainRetrofit == null) {
                createRetrofit(createOkHttp())
                _mainRetrofit!!
            } else {
                _mainRetrofit!!
            }
        }

    private fun createOkHttp() = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = BODY
            }
        )
        .addInterceptor(TokenInterceptor())
        .build()

    private fun createRetrofit(okHttpClient: OkHttpClient) {
        val moshi = MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )
        _mainRetrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(moshi)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
    }
}