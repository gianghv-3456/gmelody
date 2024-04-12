package com.sun.gmelody.data.repository.track.source

import android.content.Context
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.core.OnResultListener
import java.lang.ref.WeakReference

interface TrackDataSource {
    /**
     * Local
     */
    interface Local {
        fun getTracksLocal(
            listener: OnResultListener<MutableList<Track>>,
            context: WeakReference<Context>,
        )
    }
}
