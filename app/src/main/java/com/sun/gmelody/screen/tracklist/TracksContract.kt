package com.sun.gmelody.screen.tracklist

import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.utils.base.BasePresenter

class TracksContract {
    interface View {
        fun onGetTrackListSuccess(movies: MutableList<Movie>)
        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getTrackList()
    }
}
