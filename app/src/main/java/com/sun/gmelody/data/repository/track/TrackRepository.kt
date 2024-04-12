package com.sun.gmelody.data.repository.track

import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.core.OnResultListener

interface TrackRepository {
    fun getLocalTracks(listener: OnResultListener<MutableList<Track>>)
}
