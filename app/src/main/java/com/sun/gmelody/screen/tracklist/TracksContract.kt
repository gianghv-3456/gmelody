package com.sun.gmelody.screen.tracklist

import com.sun.gmelody.data.model.Track
import com.sun.gmelody.utils.base.BasePresenter

class TracksContract {
    interface View {
        fun onGetTrackListSuccess(tracks: MutableList<Track>)

        fun onError(exception: Exception?)
    }

    /**
     * Presenter
     */
    interface Presenter : BasePresenter<View> {
        fun getTrackList()
    }
}
