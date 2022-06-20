package com.giffinder.app.di

import android.content.Context
import com.giffinder.app.BuildConfig
import com.giffinder.app.core.data.remote.Constants.DEFAULT_TIMEOUT_IN_MIN
import com.giffinder.app.core.data.remote.Constants.MAX_CACHE_SIZE
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        Cache(get<Context>().cacheDir, MAX_CACHE_SIZE)
    }

    single {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()

        builder.cache(get())

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        builder.connectTimeout(DEFAULT_TIMEOUT_IN_MIN, TimeUnit.MINUTES)
        builder.retryOnConnectionFailure(true)

        builder.build()
    }
}
