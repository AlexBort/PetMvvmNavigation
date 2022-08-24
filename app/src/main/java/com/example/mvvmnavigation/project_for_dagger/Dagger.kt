package com.example.mvvmnavigation.project_for_dagger

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent

@Module
class AppModule {

    @Provides
    fun provideNewsRepositoryImpl(
        newsService: NewsService,
        analytics: Analytics,
    ): NewsRepositoryImpl {
        return NewsRepositoryImpl(newsService, analytics)
    }

    @Provides
    fun provideAnalytics(): Analytics {
        return Analytics()
    }
}

@Module
class NetworkModule {
    @Provides
    fun provideNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidbrodcast.dev")
            .build()
        return retrofit.create()
    }
}

//@Module
//class AnalyticsModule{
//    @Provides
//    fun provideAnalytics(): Analytics {
//        return Analytics()
//    }
//}