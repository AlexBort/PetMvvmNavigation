package com.example.mvvmnavigation

import android.app.Application
import android.content.Context
import com.example.mvvmnavigation.mob_project_for_dagger.AppOfComponent
import com.example.mvvmnavigation.mob_project_for_dagger.DaggerAppOfComponent


class App : Application() {

    lateinit var appComponent: AppOfComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppOfComponent.builder().build()
        /**
         * builder() потрібен, коли є якісь зовнішні параметри
         * і потрібно кастомізувати створення AppComponent-a,
         * або будь-якого іншого, і зарання наповнити його чимось:
         * це мб готові модулі, параметри, інтерфейс, або клас залежностей
         */
    }

    companion object {
        var repository = Repository()
    }
}
/**
 * тепер реалізовуємо отримання appComponent по всьому додатку
 *
 */
val Context.appComponent: AppOfComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }