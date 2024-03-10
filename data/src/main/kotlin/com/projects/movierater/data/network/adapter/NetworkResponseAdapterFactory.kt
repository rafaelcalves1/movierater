package com.projects.movierater.data.network.adapter

import com.projects.movierater.data.network.model.NetworkResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.IllegalStateException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException()
        }
        val responseType = getParameterUpperBound(0, returnType)
        if (NetworkResponse::class.java != getRawType(responseType)) {
            return null
        }

        if (responseType !is ParameterizedType) {
            throw IllegalStateException()
        }

        val successBody = getParameterUpperBound(0, responseType)
        val errorBody = getParameterUpperBound(1, responseType)

        return NetworkResponseAdapter<Any, Any>(successBody, errorBody)
    }
}