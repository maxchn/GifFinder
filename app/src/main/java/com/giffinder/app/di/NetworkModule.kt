package com.giffinder.app.di

import android.content.Context
import com.giffinder.app.BuildConfig
import com.giffinder.app.core.data.remote.Constants.DEFAULT_TIMEOUT_IN_MIN
import com.giffinder.app.core.data.remote.Constants.MAX_CACHE_SIZE
import com.giffinder.app.core.data.remote.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, MAX_CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideClient(cache: Cache): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()

        builder.cache(cache)

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        builder.connectTimeout(DEFAULT_TIMEOUT_IN_MIN, TimeUnit.MINUTES)
        builder.retryOnConnectionFailure(true)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    @Provides
    @Singleton
    fun provideNetworkManager(): NetworkManager {
        return NetworkManager()
    }
}
