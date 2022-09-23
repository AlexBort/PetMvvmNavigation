package com.example.mvvmnavigation.data

interface Repository<T> {
    suspend fun fetchData(): T
}