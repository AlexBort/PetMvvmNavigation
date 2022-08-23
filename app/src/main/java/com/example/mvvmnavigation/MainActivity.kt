package com.example.mvvmnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.mvvmnavigation.databinding.ActivityMainBinding
import com.example.mvvmnavigation.databinding.PartResultBinding
import com.example.mvvmnavigation.for_dagger.Computer
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var mainBinding: ActivityMainBinding? = null

    @Inject
    lateinit var computer: Computer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(mainBinding!!.root)

//        val computer: Computer = appComponent.computer()
        /**
         * instead of init (above commented code) we will do it another way
         */
        appComponent.passParamsInMainActivity(this)
    }

    val root: ViewGroup?
        get() = mainBinding!!.root
    val partResultLayout: PartResultBinding
        get() = PartResultBinding.bind(mainBinding!!.root)
}