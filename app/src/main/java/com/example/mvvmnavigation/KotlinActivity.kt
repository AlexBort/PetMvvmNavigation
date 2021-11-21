package com.example.mvvmnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class KotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        Thread {
            for (i in 0..4) {
                println("execute every step: $i")
            }
            runOnUiThread {
                Toast.makeText(
                    this@KotlinActivity,
                    "work is executed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}