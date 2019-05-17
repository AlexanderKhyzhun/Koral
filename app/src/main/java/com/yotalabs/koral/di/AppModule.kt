package com.yotalabs.koral.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.yotalabs.koral.BuildConfig
import com.yotalabs.koral.data.Api
import com.yotalabs.koral.data.Schedulers
import com.yotalabs.koral.data.impl.ApiFactory
import com.yotalabs.koral.data.impl.InterceptorFactory
import com.yotalabs.koral.data.impl.OkHttpFactory
import com.yotalabs.koral.data.impl.SchedulersImpl
import com.yotalabs.koral.data.storages.*
import com.yotalabs.koral.data.storages.impl.*
import com.yotalabs.koral.domain.*
import com.yotalabs.koral.domain.impl.*
import okhttp3.OkHttpClient
import org.koin.android.logger.AndroidLogger
import org.koin.log.EmptyLogger

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class AppModule(val context: Context) {

    val appModule = org.koin.dsl.module.module {

        /////////////////////
        //Tools
        ////////////////////
        single { PreferenceManager.getDefaultSharedPreferences(context) } bind (SharedPreferences::class)
        single { GsonBuilder().create() }
        single { OkHttpClient().newBuilder() }
        single { InterceptorFactory(get(), get()) }
        single { OkHttpFactory(get()) }
        single { get<OkHttpFactory>().buildClient().build() }
        single { ApiFactory().create(Api::class.java, BuildConfig.BASE_API_URL, get()) }
        single { SchedulersImpl() } bind (Schedulers::class)
        single { Glide.with(context) }


        /////////////////////
        // Repositories
        ////////////////////
        single { AuthRepositoryImpl(get(), get()) } bind (AuthRepository::class)
        single { StorageRepositoryImpl(get()) } bind (StorageRepository::class)
        single { SignUpCustomerRepositoryImpl(get()) } bind (SignUpCustomerRepository::class)
        single { SignUpPersonalRepositoryImpl(get()) } bind (SignUpPersonalRepository::class)


        scope("SignUp") { SignUpRepositoryImpl() } bind (SignUpRepository::class)


        /////////////////////
        // UseCases
        ////////////////////
        single { ProfileUseCaseImpl() } bind (ProfileUseCase::class)
        single { SplashUseCaseImpl(get()) } bind (SplashUseCase::class)
        single { SignUpPersonalUseCaseImpl(get(), get()) } bind (SignUpPersonalUseCase::class)
        single { SignUpCustomerUseCaseImpl(get(), get()) } bind (SignUpCustomerUseCase::class)
        single { SignUpBusinessUseCaseImpl(get(), get()) } bind (SignUpBusinessUseCase::class)
        single { ProfessionUseCaseImpl(get(), get()) } bind (ProfessionUseCase::class)
        single { ServicesUseCaseImpl(get(), get()) } bind (ServicesUseCase::class)

    }

    companion object {

        fun getLogger() = when (BuildConfig.DEBUG) {
            true -> AndroidLogger()
            false -> EmptyLogger()
        }
    }

}