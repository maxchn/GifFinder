package com.giffinder.app.di

import androidx.room.Room
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.data.local.LocalDatabase
import com.giffinder.app.data.remote.api.GifApi
import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.data.repository.gif.GifRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single { NetworkManager() }

    single {
        Room.databaseBuilder(
            get(),
            LocalDatabase::class.java,
            LocalDatabase.NAME
        ).build()
    }

    // Api's
    single<GifApi> {
        get<Retrofit>().create(GifApi::class.java)
    }

    // Repositories
    single<GifRepository> {
        GifRepositoryImpl(
            networkManager = get(),
            api = get(),
            db = get()
        )
    }
}
