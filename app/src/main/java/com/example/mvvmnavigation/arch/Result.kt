package com.example.mvvmnavigation.arch

import java.lang.Exception

typealias Mapper<Input, Output> = (Input) -> Output // how and who use it? what does it mean

sealed class Result<T> {

    fun <R> map(mapper: Mapper<T, R>? = null): Result<R> = when (this) {
        is ProgressResult -> ProgressResult()
        is ErrorResult -> ErrorResult(this.exception)
        is SuccessResult -> {
            if (mapper == null)
                throw IllegalStateException("MAPPER should not be null for success result")
            SuccessResult(mapper(this.data))
        }
    }

    fun <T> Result<T>?.takeSuccess():T? {
        return if (this is SuccessResult) this.data
        else null
    }

    class ProgressResult<T> : Result<T>()
    class SuccessResult<T>(val data: T) : Result<T>()
    class ErrorResult<T>(val exception: Exception) : Result<T>()
}
