package com.example.testproj.network

import com.example.testproj.network.RequestUrl.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
        private val interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        fun createRetrofitService(): RetrofitServiceAnnotator {
            val retrofitClient: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
            return retrofitClient.create(
                 RetrofitServiceAnnotator::
                 class.java
            )
        }
}