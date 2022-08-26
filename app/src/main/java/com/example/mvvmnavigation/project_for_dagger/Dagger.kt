package com.example.mvvmnavigation.project_for_dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create

@Component(modules = [AppModule::class])
interface AppComponent

@Module(includes = [NetworkModule::class])
abstract class AppModule {

    @Binds
    abstract fun bindsNewsRepositoryImpl_to_NewsRepository(repImpl: NewsRepositoryImpl): NewsRepository

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