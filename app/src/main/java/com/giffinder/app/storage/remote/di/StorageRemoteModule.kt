package com.giffinder.app.storage.remote.di

import com.giffinder.app.storage.remote.api.GifApi
import com.giffinder.app.storage.remote.repository.gif.GifRepository
import com.giffinder.app.storage.remote.repository.gif.GifRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import retrofit2.Retrofit

object StorageRemoteModule {
    fun get() = DI.Module("StorageRemoteModule") {
        // Api's
        bind<GifApi>() with provider { instance<Retrofit>().create(GifApi::class.java) }

        // Repositories
        bind<GifRepository>() with provider { GifRepositoryImpl(instance()) }
    }
}
