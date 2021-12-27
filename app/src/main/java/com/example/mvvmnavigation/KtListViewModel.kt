package com.example.mvvmnavigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.example.mvvmnavigation.api.ApiConnector
import com.example.mvvmnavigation.api.ManResponse
import com.example.mvvmnavigation.api.ResponseCallback
import com.example.mvvmnavigation.handle_results.ErrorResult
import com.example.mvvmnavigation.handle_results.ProgressResult
import com.example.mvvmnavigation.handle_results.SuccessResult
import com.example.mvvmnavigation.models.User
import java.lang.Exception

class KtListViewModel : BaseViewModel() {

    private val mtDataListString: MutableLiveResult<List<String>> = MutableLiveResult()
    private val mtDataString: MutableLiveResult<String> = MutableLiveResult()
    private val mtDataUser: MutableLiveResult<List<User>> = MutableLiveResult(ProgressResult())

    val dataListString: LiveResult<List<String>> = mtDataListString
    val dataString: LiveResult<String> = mtDataString
    val dataListUser: LiveResult<List<User>> = mtDataUser

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStarted() {
        ApiConnector().sendRequestInfoList(object : ResponseCallback<ManResponse> {
            override fun onSuccess(response: ManResponse) {
                mtDataUser.value = SuccessResult(response.results)
            }

            override fun onError(throwable: Throwable) {
                mtDataUser.value = ErrorResult(throwable as Exception)
            }
        }
        )
    }
}