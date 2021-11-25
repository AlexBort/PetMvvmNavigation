package com.example.mvvmnavigation

import okhttp3.Dispatcher


/**
 * Base interface for all async operations
 */
interface Task<T> {

    fun await():T

    fun enqueue(dispatcher: Dispatcher, listener: TaskListener<T>)

    suspend fun suspend(): T // будет запускать таску и превращать ее в корутину
    // continuation

    interface TaskListener<T> {}

}