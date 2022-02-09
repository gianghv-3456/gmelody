package com.sun.mvp.screen.listmovie

import com.sun.mvp.data.model.Movie
import com.sun.mvp.data.repository.MovieRepository
import com.sun.mvp.data.repository.source.remote.OnResultListener
import java.lang.Exception

class MoviesPresenter internal constructor(private val movieRepository: MovieRepository?) :
    MoviesContract.Presenter {

    private var mView: MoviesContract.View? = null

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun setView(view: MoviesContract.View?) {
        this.mView = view
    }

    override fun getMovies() {
        movieRepository?.getMovies(object : OnResultListener<MutableList<Movie>> {
            override fun onSuccess(data: MutableList<Movie>) {
                mView?.onGetMoviesSuccess(data)
            }

            override fun onError(exception: Exception?) {
                mView?.onError(exception)
            }
        })
    }
}
