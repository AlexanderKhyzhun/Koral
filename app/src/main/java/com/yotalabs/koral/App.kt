package com.yotalabs.koral

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.yotalabs.koral.di.AppModule
import com.yotalabs.koral.utils.ThreadAwareTree
import org.koin.android.ext.android.startKoin
import timber.log.Timber

/**
 * @author SashaKhyzhun
 * Created on 4/26/19.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        context = this

        startKoin(this, listOf(AppModule(this).appModule), logger = AppModule.getLogger())


        if (BuildConfig.DEBUG) {
            Timber.plant(ThreadAwareTree)
        }
        Timber.d("Application is created")

        if (BuildConfig.ENABLE_CRASHLYTICS) {
            //Fabric.with(this, Crashlytics())
        }

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }
}