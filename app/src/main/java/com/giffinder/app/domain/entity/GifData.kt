package com.giffinder.app.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifData(
    val id: String,
    val name: String,
    val remoteUrl: String,
    val localUrl: String,
    val query: String
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GifData

        if (id != other.id) return false
        if (name != other.name) return false
        if (remoteUrl != other.remoteUrl) return false
        if (query != other.query) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + remoteUrl.hashCode()
        result = 31 * result + query.hashCode()
        return result
    }
}
