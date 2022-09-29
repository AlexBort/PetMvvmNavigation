package com.example.mvvmnavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmnavigation.databinding.ActivityMainBinding
import com.example.mvvmnavigation.mvi_mvvm.MainScreenIntent
import com.example.mvvmnavigation.mvi_mvvm.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var model: MainViewModel? = null // todo: should be inited

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
        setContentView(binding.root)
        //model =        ViewModelProvider.Factory(this)
        binding.actionButton.setOnClickListener{
            model?.obtainIntent(MainScreenIntent.ClickButtonIntent)
        }
    }
}