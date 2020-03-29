package com.test.testcoolrocket.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.test.testcoolrocket.network.LoggingInterceptor
import com.test.testcoolrocket.network.UnsafeOkHttpClient
import com.test.testcoolrocket.network.service.PointsService
import com.test.testcoolrocket.repositories.PointsRepository
import com.test.testcoolrocket.viewmodels.factories.MainViewModelFactory
import okhttp3.ConnectionSpec
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import toothpick.config.Module

class AppModule : Module() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://demo.bankplus.ru/mobws/json/")
            .client(
                UnsafeOkHttpClient.getUnsafeOkHttpClient().addInterceptor(LoggingInterceptor())
                    .connectionSpecs(listOf(ConnectionSpec.COMPATIBLE_TLS)).build()
            ).addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
    }

    init {
        PointsRepository(retrofit.create(PointsService::class.java)).also {
            bind(MainViewModelFactory::class.java).toInstance(MainViewModelFactory(it))
            bind(PointsRepository::class.java).toInstance(it)
        }
    }
}