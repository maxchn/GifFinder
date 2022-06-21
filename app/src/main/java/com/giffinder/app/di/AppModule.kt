package com.giffinder.app.di

import com.giffinder.app.core.presentation.AndroidResourceReader
import com.giffinder.app.core.presentation.ResourceReader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindResourceReader(
        androidResourceReader: AndroidResourceReader
    ): ResourceReader
}
