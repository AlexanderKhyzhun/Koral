package com.yotalabs.koral.data.impl

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class ApiFactory {

    fun <T> create(clazz: Class<T>, apiUrl: String, httpClient: OkHttpClient): T {

        val gsonBuilder = GsonBuilder()
            .setLenient()
            .serializeNulls()

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .build()
            .create(clazz)
    }
}