package com.ignite.ignitesnakegame.di

import com.ignite.ignitesnakegame.data.SnakeRepository
import com.ignite.ignitesnakegame.data.SnakeRepositoryImpl
import com.ignite.ignitesnakegame.data.SnakeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080/")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

    @Provides
    fun providesSnakeService(retrofit: Retrofit): SnakeService =
        retrofit.create(SnakeService::class.java)

    @Provides
    fun providesSnakeRepository(apiService: SnakeService): SnakeRepository = SnakeRepositoryImpl(
        apiService = apiService
    )

    @Provides
    fun getOkhttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()
}