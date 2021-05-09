package com.example.mvvmnavigation.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.mvvmnavigation.services.Service_

class Service_ : Service() {
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    internal inner class Bin : Binder() {
        val service: Service_
            get() = this@Service_
    }
}