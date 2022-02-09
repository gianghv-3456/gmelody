package com.sun.mvp.screen.listmovie

import com.sun.mvp.data.model.Movie
import com.sun.mvp.utils.base.BasePresenter
import java.lang.Exception

class MoviesContract {
    /**
     * View
     */
    interface View {
        fun onGetMoviesSuccess(movies: MutableList<Movie>)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getMovies()
    }
}
