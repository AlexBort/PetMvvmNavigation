package com.example.mvvmnavigation.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.mvvmnavigation.App
import com.example.mvvmnavigation.BuildConfig
import java.lang.Exception

class TestIntentService : IntentService {

    var app: App? = null

    constructor() : super((TestIntentService::class.java.simpleName)) {
        /**
         * IntentService экстендится от обычного Service. Service в свою очередь от ContextWrapper, он же от Context.
         * Благодаря этому, из сервиса мы можем достучаться к Context-y, а соответственно к appContext, и через него комуницировать с
         * другими компонентами приложения, в данном случае с activity, которое его вызвало
         */
    }

    constructor(name: String?) : this() {

    }

    private val TAG = "TestService"

    /**
     * всегда отрабатывает в другом потоке
     */
    override fun onHandleIntent(intent: Intent?) {
        var action = intent?.action
        if (action == ACTION_DOWNLOAD_FILE) {
            var url = intent?.getStringExtra(EXTRA_URL)
            downloadFile(url)
        } else if (ACTION_CALCULATE == action) {
            calculateSomething()
        }
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

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG, "onCreate: ")
        app = application as App
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy: ")
    }

    fun calculateSomething() {
        Log.v(TAG, "calculate: ")
        Thread.sleep(5000)
        Log.v(TAG, "SUCCESS: ")
    }

    fun downloadFile(url: String?) {
        Log.v(TAG, "downloading: ")
        app!!.publishProgress(0)
//        Thread.sleep(5000)
        try {
            val count = 5
            for (i in 0..4) {
                Thread.sleep(1000)
                app!!.publishProgress(100 * (i + 1) / count)
            }
            app!!.publishCompleted()
            Log.v(TAG, "SUCCESS: ")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        /**
         * BuildConfig.APPLICATION_ID - вот такой параметр могут добавлять, чтобы этот сервис имел уникальное имя (по названию package-a проекта)
         * И если его захотят вызвать из другого приложения, чтобы это было возможно сделать благодаря тому, что имя action не совпадет с каким-то    другим
         */

        @JvmStatic
        val ACTION_DOWNLOAD_FILE = BuildConfig.APPLICATION_ID + "ACTION_DOWNLOAD_FILE"

        @JvmStatic
        val ACTION_CALCULATE = BuildConfig.APPLICATION_ID + "ACTION_CALCULATE"

        @JvmStatic
        val EXTRA_URL = "EXTRA_URL"
    }

}