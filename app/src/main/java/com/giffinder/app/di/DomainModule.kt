package com.giffinder.app.di

import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.domain.usecase.gif.BlockGifUseCase
import com.giffinder.app.domain.usecase.gif.GetGifListUseCase
import com.giffinder.app.domain.usecase.gif.UpdateGifUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetGifListUseCase(repository: GifRepository): GetGifListUseCase {
        return GetGifListUseCase(repository = repository)
    }

    @Provides
    fun provideUpdateGifUseCase(repository: GifRepository): UpdateGifUseCase {
        return UpdateGifUseCase(repository = repository)
    }

    @Provides
    fun provideBlockGifUseCase(repository: GifRepository): BlockGifUseCase {
        return BlockGifUseCase(repository = repository)
    }
}
