package com.example.mvvmnavigation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import com.example.mvvmnavigation.api.ApiConnector
import com.example.mvvmnavigation.api.ManResponse
import com.example.mvvmnavigation.api.ResponseCallback
import com.example.mvvmnavigation.models.User

class ListViewModel : ViewModel(), LifecycleObserver {

    val data = MutableLiveData<List<String>>()
    val dataString = MutableLiveData<String>()
    val users = MutableLiveData<List<User>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onViewStarted(){
        ApiConnector().sendRequestInfoList(object : ResponseCallback<ManResponse> {

            override fun onSuccess(response: ManResponse) {
                users.value = response.results
            }

            override fun onError(throwable: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    fun onViewStarted() {
//        ApiConnector().sendRequestInfoList({
//            ->
//        })


        //    List<String> list = App.repository.getTestList();
//    data.setValue(list);
//    Log.d("ListViewModel", "onViewStarted");
//
//    dataString.setValue("TEST VALUE");
    }