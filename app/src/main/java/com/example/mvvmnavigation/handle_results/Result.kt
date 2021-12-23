package com.example.mvvmnavigation.handle_results

import java.lang.Exception

// sealed classes- классы, в которых наследники будут известны на этапе компиляции проекта
sealed class Result<T>

class ProgressResult<T> : Result<T>()

class SuccessResult<T>(data: T) : Result<T>()

class ErrorResult<T>(error: Exception) : Result<T>()
