package com.example.mvvmnavigation.handlers

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi

class TestHandler {

    val handler = Handler(Looper.myLooper()!!)

    val runnableSomeWork1 = Runnable {
        someWork1()
    }

    val runnableSomeWork2 = Runnable {
        someWork1()
    }

    val runnableSomeWork3 = Runnable {
        someWork1()
    }

    val runnableSomeWork4 = Runnable {
        someWork1()
    }

    fun simplePost(){
        handler.post {
            someWork1()
        }
    }

    fun simplePostDelayed() {
        handler.postDelayed({
            someWork1()
        }, 5000)
    }

    fun postDelayedWithSeparateRunnable(){
        handler.postDelayed(runnableSomeWork1,5000)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun post(){
        handler.postDelayed(runnableSomeWork1, Token.STANDART_TOKEN,5000)
    }

    fun remove(){
        handler.removeCallbacks(runnableSomeWork1)
    }

    fun removeAllRunnables(){
        val list = arrayListOf(runnableSomeWork1,runnableSomeWork4,runnableSomeWork3,runnableSomeWork2)
        list.forEach { handler.removeCallbacks(it) }
    }

    fun removeUsingToken(){
        handler.removeCallbacksAndMessages(null)
    }

    fun someWork1(){
        println("doing some work1")
    }

    fun someWork2(){
        println("doing some work2")
    }

    fun someWork3(){
        println("doing some work3")
    }

    fun someWork4(){
        println("doing some work4")
    }

    enum class Token {
        STANDART_TOKEN
    }
}