package com.ignite.ignitesnakegame.di

import com.ignite.ignitesnakegame.domain.repository.SnakeRepository
import com.ignite.ignitesnakegame.data.repository.SnakeRepositoryImpl
import com.ignite.ignitesnakegame.data.remote.SnakeService
import com.ignite.ignitesnakegame.common.Constants.BASE_URL
import com.ignite.ignitesnakegame.domain.use_case.PostSnakeStateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

    @Provides
    @Singleton
    fun getOkhttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()

    @Provides
    @Singleton
    fun providesSnakeService(retrofit: Retrofit): SnakeService =
        retrofit.create(SnakeService::class.java)

    @Provides
    @Singleton
    fun providesSnakeRepository(apiService: SnakeService): SnakeRepository = SnakeRepositoryImpl(
        apiService = apiService
    )

    @Provides
    @Singleton
    fun providesPostSnakeMoveStateUseCase(repository: SnakeRepository) = PostSnakeStateUseCase(
        repository = repository,
    )
}