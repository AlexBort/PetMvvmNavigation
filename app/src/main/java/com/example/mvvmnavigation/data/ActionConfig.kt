package com.example.mvvmnavigation.data

data class ActionConfig(
    val type: String,
    val enabled: Boolean,
    val priority: Int,
    val valid_days: List<Int>,
    val cool_down: Long
)
