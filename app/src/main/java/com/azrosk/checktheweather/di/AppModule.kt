package com.azrosk.checktheweather.di

import com.azrosk.checktheweather.api.ApiService
import com.azrosk.checktheweather.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL: String): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideRetrofitAllExInstance(retrofit: Retrofit.Builder): ApiService =
        retrofit
            .build()
            .create(ApiService::class.java)


}