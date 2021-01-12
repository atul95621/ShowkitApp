package com.mindorks.retrofit.coroutines.data.api

import com.google.gson.GsonBuilder
import com.kit.showkitapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilder {
    private const val BASE_URL = "https://showkitapi.showkt.com/api/"
    private fun getRetrofit(): Retrofit {

        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor) //at the end
            .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}