package com.example.mvvmnavigation.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.mvvmnavigation.services.Service_

class Service_ : Service() {

    lateinit var s:String


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    fun f(){
        val str = "Hello"
        str.let {
            print(it)
        }
        str.run {
            print("dfssdf" + this)
        }
    }



    internal inner class Bin : Binder() {

        val str = "Hello"
        // this
        str.run {
            println("Длина строки: $length")
            //println("Длина строки: ${this.length}") // делает то же самое
        }


        val service: Service_
            get() = this@Service_
    }
}