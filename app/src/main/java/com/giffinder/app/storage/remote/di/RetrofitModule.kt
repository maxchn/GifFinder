package com.giffinder.app.storage.remote.di

import android.content.Context
import com.giffinder.app.BuildConfig
import com.giffinder.app.core.storage.remote.Constants.DEFAULT_TIMEOUT_IN_MIN
import com.giffinder.app.core.storage.remote.Constants.MAX_CACHE_SIZE
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val TAG_BASE_URL = "baseUrl"

object RetrofitModule {
    fun get() = DI.Module("RetrofitModule") {
        bind<String>(tag = TAG_BASE_URL) with singleton { BuildConfig.BASE_URL }

        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                .baseUrl(instance<String>(TAG_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(instance())
                .build()
        }

        bind<OkHttpClient>() with singleton {
            val builder: OkHttpClient.Builder = OkHttpClient.Builder()

            builder.cache(instance())

            if (BuildConfig.DEBUG) {
                Timber.wtf("Enable")
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }

            builder.connectTimeout(DEFAULT_TIMEOUT_IN_MIN, TimeUnit.MINUTES)
            builder.retryOnConnectionFailure(true)

            builder.build()
        }

        bind<Cache>() with provider {
            Cache(instance<Context>().cacheDir, MAX_CACHE_SIZE)
        }
    }
}
