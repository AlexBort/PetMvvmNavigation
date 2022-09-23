package com.example.mvvmnavigation.mvi_mvvm

import androidx.lifecycle.viewModelScope
import com.example.mvvmnavigation.data.ActionConfigRepository
import com.example.mvvmnavigation.data.Repository
import com.example.mvvmnavigation.data.handleConfigActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : BaseViewModel() {

    override fun obtainIntent(intent: BaseUserIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainScreenIntent.ClickButtonIntent -> {
                    val listConfigs = withContext(Dispatchers.IO) {
                        ActionConfigRepository().fetchData()
                    }
                    withContext(Dispatchers.Main) {
                        listConfigs.handleConfigActions { actionType ->
                            println(actionType.toString())
                        }
                    }
                }
            }
        }
    }

}


//viewModelScope.launch {
//    when (intent) {
//        is BaseUserIntent.UserFetch -> {
//            mutableLiveDataState.value = State.LoadingState()
//            mutableLiveDataState.let { state ->
//
//                val listData =  withContext(Dispatchers.IO) {
////                      RemoteRepository().getData()
//                    SavedRepository().getData().ifEmpty { RemoteRepository().getData() }}
//
//                withContext(Dispatchers.Main) {
//                    if (listData.isEmpty()) {
//                        state.value = State.EmptyDataState()
//                    } else {
//                        state.value = State.SuccessState(listData)
//                    } }
//            }
//        }
//        is ListUserIntent.ClickItemIntent -> {
//            mutableLiveDataState.value = ListScreenState.FinishingScreenState(intent.position)
//        }
//    }
//}