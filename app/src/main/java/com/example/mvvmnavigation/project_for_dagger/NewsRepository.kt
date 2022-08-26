package com.example.mvvmnavigation.project_for_dagger

import javax.inject.Inject
import javax.inject.Named

interface NewsRepository {

    suspend fun getNews(newsId: String): News
}

class NewsRepositoryImpl @Inject constructor(
    @Named("prod")
    private val newsService: NewsService,
    private val analytics: Analytics,
) : NewsRepository {

    override suspend fun getNews(newsId: String): News {
        analytics.trackNewsRequest(newsId)
        return newsService.news(newsId)
    }
}