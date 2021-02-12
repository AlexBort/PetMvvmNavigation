package com.example.mvvmnavigation.api

import com.example.mvvmnavigation.models.Inform
import com.example.mvvmnavigation.models.User

data class ManResponse(val results: List<User>, val inform: Inform) : IResponse {
}