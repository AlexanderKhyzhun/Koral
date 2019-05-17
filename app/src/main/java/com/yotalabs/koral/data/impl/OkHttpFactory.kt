package com.yotalabs.koral.data.impl

import com.yotalabs.koral.BuildConfig
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpFactory(private val interceptorFactory: InterceptorFactory) {

    fun buildClient(): OkHttpClient.Builder {
        return when (BuildConfig.DEBUG) {
            true -> buildClientDebug()
            false -> buildClientRelease()
        }
    }

    private fun buildClientRelease() = with(OkHttpClient().newBuilder()) {
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        //addInterceptor(interceptorFactory.cookieInterceptor())
        addInterceptor(interceptorFactory.localeInterceptor())
        addInterceptor(interceptorFactory.tokenInterceptor())
        addInterceptor(interceptorFactory.responseInterceptor())
    }

    private fun buildClientDebug() = with(OkHttpClient().newBuilder()) {
        connectTimeout(60, TimeUnit.SECONDS)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
        //addInterceptor(interceptorFactory.cookieInterceptor())
        addInterceptor(interceptorFactory.localeInterceptor())
        addInterceptor(interceptorFactory.loggingInterceptor())
        addInterceptor(interceptorFactory.tokenInterceptor())
        addInterceptor(interceptorFactory.responseInterceptor())
    }

}