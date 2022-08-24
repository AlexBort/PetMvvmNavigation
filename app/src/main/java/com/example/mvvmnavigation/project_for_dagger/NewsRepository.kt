package com.example.mvvmnavigation.project_for_dagger

interface NewsRepository {

    suspend fun getNews(newsId: String): News
}

class NewsRepositoryImpl(
    private val newsService: NewsService,
    private val analytics: Analytics,
) : NewsRepository {

    override suspend fun getNews(newsId: String): News {
        analytics.trackNewsRequest(newsId)
        return newsService.news(newsId)
    }
}