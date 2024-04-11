package com.sun.gmelody.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track(var id: String, var name: String, var artist: String, var duration: String): Parcelable
