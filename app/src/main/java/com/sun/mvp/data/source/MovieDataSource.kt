package com.sun.mvp.data.source

import com.sun.mvp.data.model.Movie
import com.sun.mvp.data.source.remote.OnResultListener

interface MovieDataSource {
    /**
     * Local
     */
    interface Local

    /**
     * Remote
     */
    interface Remote {
        fun getMovies(listener: OnResultListener<MutableList<Movie>>)
    }
}