package com.sun.mvp.data.repository

import com.sun.mvp.data.model.Movie
import com.sun.mvp.data.repository.source.MovieDataSource
import com.sun.mvp.data.repository.source.remote.OnResultListener

class MovieRepository private constructor(
    private val remote: MovieDataSource.Remote,
    private val local: MovieDataSource.Local
) : MovieDataSource.Local, MovieDataSource.Remote {
    override fun getMoviesLocal(listener: OnResultListener<MutableList<Movie>>) {
        TODO("Not yet implemented")
    }

    override fun getMovies(listener: OnResultListener<MutableList<Movie>>) {
        remote.getMovies(listener)
    }

    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(remote: MovieDataSource.Remote, local: MovieDataSource.Local) =
            synchronized(this) {
                instance ?: MovieRepository(remote, local).also { instance = it }
            }
    }
}
