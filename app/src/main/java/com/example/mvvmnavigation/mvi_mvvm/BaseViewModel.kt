package com.example.mvvmnavigation.mvi_mvvm

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun obtainIntent(intent: BaseUserIntent)
}