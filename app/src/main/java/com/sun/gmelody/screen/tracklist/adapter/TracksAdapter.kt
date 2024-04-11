package com.sun.gmelody.screen.tracklist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.utils.OnItemRecyclerViewClickListener

class TracksAdapter: RecyclerView.Adapter<> {
    private val tracks = mutableListOf<Track>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Track>? = null

    
}
