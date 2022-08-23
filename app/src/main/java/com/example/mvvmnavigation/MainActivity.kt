package com.example.mvvmnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.mvvmnavigation.databinding.ActivityMainBinding
import com.example.mvvmnavigation.databinding.PartResultBinding

class MainActivity : AppCompatActivity() {
    var mainBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(mainBinding!!.root)

    }

    val root: ViewGroup?
        get() = mainBinding!!.root
    val partResultLayout: PartResultBinding
        get() = PartResultBinding.bind(mainBinding!!.root)
}