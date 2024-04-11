package com.sun.gmelody.screen.listmovie

import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.utils.base.BasePresenter
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
