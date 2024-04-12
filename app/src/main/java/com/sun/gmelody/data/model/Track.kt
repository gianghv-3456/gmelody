package com.sun.gmelody.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track(
    val id: Long,
    val title: String,
    val displayName: String,
    val artist: String,
    val duration: Long,
    val data: String,
) :
    Parcelable
