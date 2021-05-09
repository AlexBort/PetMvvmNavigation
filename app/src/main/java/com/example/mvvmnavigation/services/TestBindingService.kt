package com.example.mvvmnavigation.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.mvvmnavigation.MainActivity

/**
 *  binding (связывание)
 *  - используется для синхронной коммуникации
 *  - Когда у нас нет каких-то тяжелых задач
 *  - Для случая, когда нам нужно прослушывать какой-то результат
 */
class TestBindingService : Service() {

    var testBinder = TestBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return testBinder
    }

    // we don't need this method as in simple service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    fun getSettings(): String = "settings"

    inner class TestBinder : Binder() {

        val service: TestBindingService
            get() {
                return this@TestBindingService
            }

    }

}