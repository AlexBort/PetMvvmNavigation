package com.example.mvvmnavigation.project_for_dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create


@Component(modules = [AppModule::class])
interface AppComponent

@Module(includes = [NetworkModule::class, AppBindModule::class])
 class AppModule
/**
 * ми видалили @Provides, і замість цього реалізовуємо @Inject в конструктарах тих класів,
 * які отримували раніше за допомогою @Provides
 */

@Module
interface AppBindModule {
    @Binds
    fun bindsNewsRepositoryImpl_to_NewsRepository(repImpl: NewsRepositoryImpl): NewsRepository
}

@Module
class NetworkModule {

    @Provides
    fun provideProdNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidbrodcast.dev")
            .build()
        return retrofit.create()
    }

    @Provides
    fun provideStageNewsService(): NewsService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://stage.androidbrodcast.dev")
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