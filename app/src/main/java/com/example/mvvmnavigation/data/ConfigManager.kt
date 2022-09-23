package com.example.mvvmnavigation.data

enum class ActionType {
    NONE,

    ANIMATION {
        override fun toString() = "animation"
    },
    TOAST {
        override fun toString() = "toast"
    },
    CALL {
        override fun toString() = "call"
    },
    NOTIFICATION {
        override fun toString() = "notification"
    }
}

fun String.defineTypeAction(): ActionType {
    return when (this) {
        ActionType.ANIMATION.toString() -> {
            ActionType.ANIMATION
        }
        ActionType.TOAST.toString() -> {
             ActionType.TOAST
        }
        ActionType.CALL.toString() -> {
             ActionType.CALL
        }
        ActionType.NOTIFICATION.toString() -> {
             ActionType.NOTIFICATION
        }
        else -> {ActionType.NONE}
    }
}

fun List<ActionConfig>.handleConfigActions(getActionType: (ActionType)->(Unit)) {
    filter { it.enabled }.let { list ->
        list.sortedWith(compareByDescending { it.priority })
        getActionType.invoke(list[0].type.defineTypeAction())
    }
}