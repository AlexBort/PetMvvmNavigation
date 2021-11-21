package com.example.mvvmnavigation.handlers

import android.os.Handler
import android.os.Looper
import android.os.Message

class MessageHandler {

    private val messageHandler = Handler(Looper.myLooper()!!) {
        // here we receive messages
        if (it.what == 0) {
            doSomeWork()
        }
        return@Handler true }


    fun doSomeWork() {}

    fun testFun(){
        messageHandler.sendEmptyMessage(0)
        messageHandler.sendMessage(Message())
    }

    fun obtain(){
            messageHandler.obtainMessage()
    }

    fun launchOperationWithOwnCallback(){
        val message = Message.obtain(messageHandler) {
            doOwnWork()
        }
        messageHandler.sendMessage(message)
    }

    fun doOwnWork(){}
}