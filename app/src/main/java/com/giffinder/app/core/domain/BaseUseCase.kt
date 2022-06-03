package com.giffinder.app.core.domain

import com.giffinder.app.core.presentation.Resource
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

private const val UNEXPECTED_ERROR = "An unexpected error occurred"
private const val INTERNET_CONNECTION_ERROR =
    "Couldn't reach server. Check your internet connection"

abstract class BaseUseCase {

    suspend fun <T, M> executeRemote(
        call: suspend (() -> T),
        mapper: ((item: T) -> M)
    ): Resource<M> {
        return try {
            val data = call.invoke()

            Resource.Success(data = mapper.invoke(data))
        } catch (e: HttpException) {
            Timber.wtf(e)
            Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR)
        } catch (e: IOException) {
            Timber.wtf(e)
            Resource.Error(INTERNET_CONNECTION_ERROR)
        }
    }
}
