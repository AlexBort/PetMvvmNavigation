package com.example.mvvmnavigation.mvi_mvvm

sealed class BaseState {
    object LoadingState : BaseState()
}

sealed class ActionState : BaseState() {
    object LoadingState : ActionState()
    object AnimationState : ActionState()
    object CallState : ActionState()
    object NotificationState : ActionState()
}

sealed class BaseUserIntent {
    object ClickButtonIntent : BaseUserIntent()
}

sealed class MainScreenIntent : BaseUserIntent() {
    object ClickButtonIntent : BaseUserIntent()
}