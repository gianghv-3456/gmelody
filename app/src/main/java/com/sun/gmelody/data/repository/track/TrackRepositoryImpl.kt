package com.sun.gmelody.data.repository.track

import android.content.Context
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.core.OnResultListener
import com.sun.gmelody.data.repository.track.source.local.TrackLocalDataSource
import java.lang.ref.WeakReference

class TrackRepositoryImpl private constructor(
    private val localSource: TrackLocalDataSource,
    private val context: WeakReference<Context>,
) : TrackRepository {
    override fun getLocalTracks(listener: OnResultListener<MutableList<Track>>) {
        localSource.getTracksLocal(listener, context)
    }

    companion object {
        private var instance: TrackRepository? = null

        fun getInstance(
            localSource: TrackLocalDataSource,
            context: WeakReference<Context>,
        ) = synchronized(this) {
            instance ?: TrackRepositoryImpl(localSource, context).also { instance = it }
        }
    }
}
