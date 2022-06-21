package com.giffinder.app.di

import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.data.repository.gif.GifRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGifRepository(
        repository: GifRepositoryImpl
    ): GifRepository
}
