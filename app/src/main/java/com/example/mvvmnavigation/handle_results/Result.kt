package com.example.mvvmnavigation.handle_results

import java.lang.Exception

// sealed classes- классы, в которых наследники будут известны на этапе компиляции проекта
sealed class Result<T>

class ProgressResult<T> : Result<T>()

class SuccessResult<T>(val data: T) : Result<T>()

class ErrorResult<T>(error: Exception) : Result<T>()

// adding extension function for defining success result:
fun <T> Result<T>?.takeSuccess(): T? {
    return if (this is SuccessResult) {
        this.data
    } else null
}