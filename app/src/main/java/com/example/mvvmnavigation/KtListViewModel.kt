package com.example.mvvmnavigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.example.mvvmnavigation.api.ApiConnector
import com.example.mvvmnavigation.api.ManResponse
import com.example.mvvmnavigation.api.ResponseCallback
import com.example.mvvmnavigation.models.User

class KtListViewModel : BaseViewModel() {

    private val mtDataListString: MutableLiveResult<List<String>> = MutableLiveData()
    private val mtDataString: MutableLiveResult<String> = MutableLiveData()
    private val mtDataUser: MutableLiveResult<List<User>> = MutableLiveData()

    val dataListString: LiveResult<List<String>> = mtDataListString
    val dataString: LiveResult<String> = mtDataString
    val dataListUser: LiveResult<List<User>> = mtDataUser

//    fun getDataString(): MutableLiveData<String?>? {
//        return mtDataString
//    }

//    fun getUsersList(): MutableLiveData<List<User?>?>? {
//        return users
//    }

//    fun getData(): LiveData<List<String?>?>? {
//        return data
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStarted() {
        ApiConnector().sendRequestInfoList(object : ResponseCallback<ManResponse>{
            override fun onSuccess(response: ManResponse) {
                mtDataUser.value = response.results
            }

            override fun onError(throwable: Throwable) {

            }
        }
        )
    }
}