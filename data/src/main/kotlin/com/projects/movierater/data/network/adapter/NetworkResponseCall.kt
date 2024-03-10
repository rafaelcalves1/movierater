package com.projects.movierater.data.network.adapter

import com.projects.movierater.data.network.model.NetworkResponse
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.lang.UnsupportedOperationException

internal class NetworkResponseCall<SUCCESS : Any, EXCEPTION : Any>(
    private val delegate: Call<SUCCESS>,
    private val errorConverter: EXCEPTION
) : Call<NetworkResponse<SUCCESS, EXCEPTION>> {

    override fun enqueue(callback: Callback<NetworkResponse<SUCCESS, EXCEPTION>>) {
        return delegate.enqueue(
            object : Callback<SUCCESS> {
                override fun onResponse(call: Call<SUCCESS>, response: Response<SUCCESS>) {

                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkResponse.Success(it)
                                )
                            )
                        } ?: callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(
                                NetworkResponse.ApiError(Exception())
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(Exception()))
                        )
                    }
                }

                override fun onFailure(call: Call<SUCCESS>, t: Throwable) {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.ApiError(Exception()))
                    )
                }
            }
        )
    }

    override fun execute(): Response<NetworkResponse<SUCCESS, EXCEPTION>> {
        throw UnsupportedOperationException()
    }


    override fun clone() = NetworkResponseCall(delegate.clone(), errorConverter)
    override fun isExecuted() = delegate.isExecuted
    override fun cancel() = delegate.cancel()
    override fun isCanceled() = delegate.isCanceled
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()


}