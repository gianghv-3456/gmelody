package com.sun.mvp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var backDropImage: String = "",
    var overView: String = "",
    var vote: Int = 0,
    var voteCount: Int = 0,
    var title: String = "",
    var urlImage: String = "",
    var originalTitle: String = ""
) : Parcelable

object MovieEntry {
    const val OVERVIEW = "overview"
    const val BACKDROP_IMAGE = "backdrop_path"
    const val MOVIES = "results"
    const val VOTE = "vote_average"
    const val TITLE = "title"
    const val VOTE_COUNT = "vote_count"
    const val URL_IMAGE = "poster_path"
    const val ORIGINAL_TITLE = "original_title"
}
