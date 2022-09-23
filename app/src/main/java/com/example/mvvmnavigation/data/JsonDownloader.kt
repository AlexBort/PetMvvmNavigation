package com.example.mvvmnavigation.data

import java.util.*

class JsonDownloader : Connector<List<ActionConfig>> {

    override val BASE_URL: String
        get() = "https://s3-us-west-2.amazonaws.com/androidexam/butto_to_action_config.json"

    override suspend fun connect(): List<ActionConfig> {
        // todo - should be implemented
        return ArrayList<ActionConfig>()
    }
}