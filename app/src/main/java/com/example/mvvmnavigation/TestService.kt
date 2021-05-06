package com.example.mvvmnavigation

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder

class TestService(name: String?) : IntentService(name) {


    /**
     * всегда отрабатывает в другом потоке
     */
    override fun onHandleIntent(intent: Intent?) {

    }
    /**
     * Если служба уже запущена, ты не можешь создать еще один instance service
     * Все команды, которые передаются, складываются в очередь и будут отрабатывать последовательно друг за другом.
        *
     */

    /**
     * Все службы наследуются от Service.
    Но есть IntentService, который также наследуется от Service
     */

    /**
     * этот метод не нужно переопределять, он уже переопределен с возвратном нуля
     */
//    fun onBind()

}