package com.example.mvvmnavigation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRequestsApi {
    @GET("api/")
    fun getResults(@Query("results") results: Int?): Call<ManResponse?>?
}