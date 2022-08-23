package com.example.mvvmnavigation

import android.app.Application
import android.content.Context
import com.example.mvvmnavigation.for_dagger.AppComponent
import com.example.mvvmnavigation.for_dagger.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        var repository = Repository()
    }
}
/**
 * тепер реалізовуємо отримання appComponent по всьому додатку
 *
 */
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }