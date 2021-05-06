package com.example.mvvmnavigation

import android.app.Application
import android.os.Handler
import android.os.Looper
import java.util.*

class App : Application() {

    private var listeners: MutableSet<TaskListener> = HashSet()
    private var handler = Handler(Looper.getMainLooper()) // в каком потоке необходимо указывать, чтобы случайно не заюзать хендлер для другого потока

    override fun onCreate() {
        super.onCreate()
        //    Repository repository = new Repository();
    }

    companion object {
        var repository = Repository()
    }

    fun addListener(taskListener: TaskListener) {
        listeners.add(taskListener)
    }

    fun removeListener(taskListener: TaskListener) {
        listeners.remove(taskListener)
    }

    fun publishCompleted() {
        handler.post(object : Runnable {
            override fun run() {
                for (taskListener in listeners) {
                    taskListener.onCompleted()
                }
            }

        })

    }

    fun publishProgress(percents: Int) {
        handler.post {
            for (taskListener in listeners) {
                taskListener.onProgressChanged(percents)
            }
        }
      
    }
}