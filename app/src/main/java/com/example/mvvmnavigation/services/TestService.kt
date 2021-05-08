package com.example.mvvmnavigation.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.example.mvvmnavigation.App
import java.lang.Exception

class TestService : Service() {

    private val TAG = "TestService"
    var app: App? = null

    var counter = 0
    /* In HandleIntent  service after finishing of all operations have possibility to stop self under the hood
    But in simple service we don't have such possibility, therefore we should handle stopping of service by hands.
    So we should count number of sending events to service, and when the last service finish his work we should call stopSelf() for it
     */

    var handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()
        app = application as App
    }

    /**for simple service class we should override method onBind()
     * But for intent service we shouldn't do this because it has overrided  with returning of null
     */
    override fun onBind(intent: Intent?): IBinder? {
        // он необходим для привязки служб через binder
        return null;
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var action = intent?.action
        if (action == TestIntentService.ACTION_DOWNLOAD_FILE) {
            var url = intent?.getStringExtra(TestIntentService.EXTRA_URL)
            counter++
            /* such service works in main thread
            therefore if we work with long operations
            we should create new work thread manually (if to compare this case with IntentService where it's made under the hood)
             */
            Thread(object : Runnable {
                override fun run() {
                    downloadFile(url)
                }
            }).start()

        } /*else if (TestIntentService.ACTION_CALCULATE == action) {
            calculateSomething()
        }*/
        // в этом методе нужно обязательно вернуть какое-то константное значение, каждое из них имеет определенный смысл
        return START_NOT_STICKY;
        /*START_NOT_STICKY*/ /*START_STICKY*/ /*START_REDELIVER_INTENT*/
//        START_NOT_STICKY - система не будет пытаться восстанавливать вашу службу, если служба была убита системой
//        START_STICKY - это если нам нужно, чтобы нам пересоздали служубу при перевой же возможности. Но нет на это гарантии.
        //        Когда служба пересоздастся, то intent, который мы передавали, будет null
        // START_REDELIVER_INTENT - после пересоздания службы, система нам вернет последний переданный в этот метод intent
    }

    fun calculateSomething() {
        Log.v(TAG, "calculate: ")
        Thread.sleep(5000)
        Log.v(TAG, "SUCCESS: ")
    }

    fun downloadFile(url: String?) {
        Log.v(TAG, "downloading: ")
        handler.post(object : Runnable {
            override fun run() {
                app!!.publishProgress(0)
            }

        })

//        Thread.sleep(5000)
        try {
            val count = 5
            for (i in 0..4) {
                Thread.sleep(1000)
                handler.post(object : Runnable {
                    override fun run() {
                        app!!.publishProgress(100 * (i + 1) / count)
                    }
                })

            }
            handler.post {
                app!!.publishCompleted()
            }

            Log.v(TAG, "SUCCESS: ")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        handler.post {
            object : Runnable {
                override fun run() {
                    counter--
                    if (counter == 0) {
                        stopSelf()
                    }
                }

            }
        }
    }

}