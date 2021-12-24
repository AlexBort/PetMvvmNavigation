package com.example.mvvmnavigation

import androidx.lifecycle.*

/*
typealias - це будуть як альтернативні назви для типів LiveData,
в яких буде знаходитися Result<T>
* */
typealias LiveResult<T> = LiveData<Result<T>>
typealias MutableLiveResult<T> = MutableLiveData<Result<T>>
typealias MediatorLiveResult<T> = MediatorLiveData<Result<T>>

open class BaseViewModel : ViewModel(), LifecycleObserver {

    open fun onResult(result: Any) {

    }

}