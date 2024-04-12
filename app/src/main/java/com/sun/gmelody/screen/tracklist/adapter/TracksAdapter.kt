package com.sun.gmelody.screen.tracklist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sun.gmelody.R
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.utils.OnItemRecyclerViewClickListener
import com.sun.gmelody.utils.ext.notNull

class TracksAdapter : RecyclerView.Adapter<TracksAdapter.ViewHolder>() {
    private val tracks = mutableListOf<Track>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Track>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_track, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    fun updateData(tracks: MutableList<Track>?) {
        tracks.notNull {
            this.tracks.clear()
            this.tracks.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bindViewData(tracks[position])
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    class ViewHolder(
        itemView: View,
        itemClickListener: OnItemRecyclerViewClickListener<Track>?,
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var mTextViewTitle: TextView? = null
        private var mTextViewDuration: TextView? = null
        private var mTextViewContent: TextView? = null
        private var mImageViewIconThumbnail: ImageView? = null

        private var trackData: Track? = null
        private var listener: OnItemRecyclerViewClickListener<Track>? = null

        init {
            mTextViewTitle = itemView.findViewById(R.id.textViewTitle)
            mTextViewDuration = itemView.findViewById(R.id.textViewDuration)
            mTextViewContent = itemView.findViewById(R.id.textViewContent)
            mImageViewIconThumbnail = itemView.findViewById(R.id.imageThumbnail)
            itemView.setOnClickListener(this)
            listener = itemClickListener
        }

        fun bindViewData(track: Track) {
            track.let {
                mTextViewTitle?.text = it.title
                mTextViewDuration?.text = it.duration.toString()
                mTextViewContent?.text = it.artist
//                mImageViewIconThumbnail?.loadImageCircleWithUrl(it.)
                trackData = it
            }
        }

        override fun onClick(view: View?) {
            listener?.onItemClick(trackData)
        }
    }
}
