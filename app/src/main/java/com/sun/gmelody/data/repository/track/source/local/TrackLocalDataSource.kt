package com.sun.gmelody.data.repository.track.source.local

import android.content.Context
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.core.OnResultListener
import com.sun.gmelody.data.repository.track.source.TrackDataSource
import com.sun.gmelody.utils.ext.isNull
import com.sun.gmelody.utils.ext.notNull
import com.sun.gmelody.utils.music.MediaGetter
import java.lang.Exception
import java.lang.ref.WeakReference

class TrackLocalDataSource : TrackDataSource.Local {
    override fun getTracksLocal(
        listener: OnResultListener<MutableList<Track>>,
        context: WeakReference<Context>,
    ) {
        val musicList = context.get()?.let { MediaGetter.queryLocalMusic(it) }
        musicList.notNull { listener.onSuccess(it) }
        musicList.isNull { listener.onError(Exception("No Music found!")) }
    }

    companion object {
        private var instance: TrackLocalDataSource? = null

        fun getInstance() =
            synchronized(this) {
                instance ?: TrackLocalDataSource().also { instance = it }
            }
    }
}
