package com.example.mvvmnavigation

interface TaskListener {

    fun onProgressChanged(percents: Int)

    fun onCompleted()
}