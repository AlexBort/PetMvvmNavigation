package com.example.mvvmnavigation.data

interface Connector<D> {
    val BASE_URL: String
    suspend fun connect():D
}