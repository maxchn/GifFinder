package com.giffinder.app.data.di

import androidx.room.Room
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.data.local.LocalDatabase
import com.giffinder.app.data.remote.api.GifApi
import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.data.repository.gif.GifRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import retrofit2.Retrofit

object DataModule {
    fun get() = DI.Module("DataModule") {
        bind<NetworkManager>() with singleton { NetworkManager() }

        bind<LocalDatabase>() with singleton {
            Room.databaseBuilder(
                instance(),
                LocalDatabase::class.java,
                LocalDatabase.NAME
            ).build()
        }

        // Api's
        bind<GifApi>() with provider { instance<Retrofit>().create(GifApi::class.java) }

        // Repositories
        bind<GifRepository>() with provider {
            GifRepositoryImpl(
                instance(),
                instance(),
                instance()
            )
        }
    }
}
