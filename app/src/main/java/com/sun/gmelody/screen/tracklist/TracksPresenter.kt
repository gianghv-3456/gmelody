package com.sun.gmelody.screen.tracklist

import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.core.OnResultListener
import com.sun.gmelody.data.repository.track.TrackRepository

class TracksPresenter internal constructor(private val repository: TrackRepository) : TracksContract.Presenter {
    private var mView: TracksContract.View? = null

    override fun getTrackList() {
        repository.getLocalTracks(
            object : OnResultListener<MutableList<Track>> {
                override fun onSuccess(data: MutableList<Track>) {
                    mView?.onGetTrackListSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    mView?.onError(exception)
                }
            },
        )
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun setView(view: TracksContract.View?) {
        this.mView = view
    }
}
