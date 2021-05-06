package com.example.mvvmnavigation

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder

class TestService(name: String?) : IntentService(name) {

    override fun onHandleIntent(intent: Intent?) {
        TODO("Not yet implemented")
    }
    /**
     * Все службы наследуются от Service.
    Но есть IntentService, который также наследуется от Service
     */

    /**
     * этот метод не нужно переопределять, он уже переопределен с возвратном нуля
     */
//    fun onBind()

}