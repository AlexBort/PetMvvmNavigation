package com.example.mvvmnavigation.api

interface ResponseCallback<T : IResponse?> {
    fun onSuccess(response: T)
    fun onError(throwable: Throwable)
}