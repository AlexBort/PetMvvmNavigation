package com.example.mvvmnavigation.mvi_mvvm

interface ActionHandler {
    fun handleAnimationAction()
    fun handleToastAction()
    fun handleCallAction()
    fun handleNotificationAction()
}