package com.giffinder.app.di

import android.content.Context
import androidx.room.Room
import com.giffinder.app.data.local.LocalDatabase
import com.giffinder.app.data.remote.api.GifApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            LocalDatabase.NAME
        ).build()
    }

    // Api's
    @Provides
    fun provideGifApi(retrofit: Retrofit): GifApi {
        return retrofit.create(GifApi::class.java)
    }
}
