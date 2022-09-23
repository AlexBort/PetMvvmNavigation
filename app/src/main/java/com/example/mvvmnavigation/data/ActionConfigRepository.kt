package com.example.mvvmnavigation.data

class ActionConfigRepository : Repository<List<ActionConfig>> {

    private val downloader = JsonDownloader()

    override suspend fun fetchData(): List<ActionConfig> {
        return downloader.connect()
    }

}