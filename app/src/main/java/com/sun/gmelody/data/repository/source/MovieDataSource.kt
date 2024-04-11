package com.sun.gmelody.data.repository.source

import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.data.repository.source.remote.OnResultListener

interface MovieDataSource {
    /**
     * Local
     */
    interface Local {
        fun getMoviesLocal(listener: OnResultListener<MutableList<Movie>>)
    }

    /**
     * Remote
     */
    interface Remote {
        fun getMovies(listener: OnResultListener<MutableList<Movie>>)
    }
}
