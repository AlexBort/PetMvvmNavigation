package com.example.mvvmnavigation

import android.app.IntentService
import android.content.Intent
import android.util.Log

class TestService() : IntentService(TestService::class.java.simpleName) {

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
        Thread.sleep(5000)
        Log.v(TAG, "SUCCESS: ")
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