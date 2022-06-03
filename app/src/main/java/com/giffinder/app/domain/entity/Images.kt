package com.giffinder.app.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(val downsized: Downsized?) : Parcelable
