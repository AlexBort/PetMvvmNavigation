package com.example.mvvmnavigation.handle_results

import java.lang.Exception
import java.lang.IllegalStateException

typealias Mapper<Input,Output> = (Input) -> Output

// sealed classes- классы, в которых наследники будут известны на этапе компиляции проекта
sealed class Result<T> {
    fun <R> map(mapper: Mapper<T, R>? = null): Result<R> = when(this){
         is ProgressResult -> ProgressResult()
        is ErrorResult -> ErrorResult(this.error)
        is SuccessResult -> {
            if (mapper==null){
                throw IllegalStateException("Mapper should not be null for success result")
            }
            SuccessResult(mapper(this.data))
        }
    }

} // T - будет входящий тип{
// исходящий тип будет R

class ProgressResult<T> : Result<T>()

class SuccessResult<T>(val data: T) : Result<T>()

class ErrorResult<T>(val error: Exception) : Result<T>()

// adding extension function for defining success result:
fun <T> Result<T>?.takeSuccess(): T? {
    return if (this is SuccessResult) {
        this.data
    } else null
}