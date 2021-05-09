package com.example.mvvmnavigation.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import com.example.mvvmnavigation.MainActivity
import com.example.mvvmnavigation.TaskListener
import java.lang.Exception

/**
 *  binding (связывание)
 *  - используется для синхронной коммуникации
 *  - Когда у нас нет каких-то тяжелых задач
 *  - Для случая, когда нам нужно прослушывать какой-то результат
 */
class TestBindingService : Service() {

    var testBinder = TestBinder()
    val handler: Handler = Handler(Looper.getMainLooper())
    var thread: Thread? = null

    override fun onBind(intent: Intent?): IBinder? {
        return testBinder
    }

    // we don't need this method as in simple service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    fun getSettings(): String = "settings"

    fun downloadFile(url: String, taskListener: TaskListener) {
        if (thread != null) return
        thread = Thread(object : Runnable {
            override fun run() {
                handler.post {
                    taskListener.onProgressChanged(0)
                    try {
                        Thread.sleep(5000)
                        handler.post { taskListener.onCompleted() }
                    } catch (e: Exception) {
                    }
                    thread = null
                }
            }
        })
        thread!!.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        thread!!.interrupt()
    }

    inner class TestBinder : Binder() {

        val service: TestBindingService
            get() {
                return this@TestBindingService
            }

    }

}