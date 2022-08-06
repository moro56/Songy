package it.emperor.songy.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val gson = GsonBuilder().create()

    private val okhttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://rss.applemarketingtools.com/api")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okhttpClient)
            .build()
    }

    fun <T> createApiService(apiServiceClass: Class<T>) = retrofit.create(apiServiceClass) as T
}