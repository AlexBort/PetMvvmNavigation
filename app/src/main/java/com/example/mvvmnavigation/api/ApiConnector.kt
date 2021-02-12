package com.example.mvvmnavigation.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnector {
    private val BASE_URL = "https://randomuser.me/"
    private var requestsApi: IRequestsApi

    init {
        requestsApi = createRetrofitBuilder()!!.create<IRequestsApi>(IRequestsApi::class)
    }

    private fun createRetrofitBuilder(): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor) /*.readTimeout(5, TimeUnit.MINUTES)*/
                .build()
        return Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun sendRequestInfoList(listener: ResponseCallback) {
        val call: Call<IResponse> = requestsApi.getResults(20)
        call.enqueue(object : Callback<ServerResponse?> {
            override fun onResponse(call: Call<ServerResponse?>, response: Response<ServerResponse?>) {
                listener.successResponse(response.body())
            }

            override fun onFailure(call: Call<ServerResponse?>, t: Throwable) {
                listener.failureResponse()
            }
        })
    }

}