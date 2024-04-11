package com.sun.gmelody.data.repository.source.local

import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.data.repository.source.MovieDataSource
import com.sun.gmelody.data.repository.source.remote.OnResultListener

class MovieLocalDataSource : MovieDataSource.Local {

    override fun getMoviesLocal(listener: OnResultListener<MutableList<Movie>>) {
        TODO("Not yet implemented")
    }

    companion object {
        private var instance: MovieLocalDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: MovieLocalDataSource().also { instance = it }
        }
    }
}
