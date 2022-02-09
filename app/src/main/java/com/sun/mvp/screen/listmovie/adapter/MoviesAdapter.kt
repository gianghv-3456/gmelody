package com.sun.mvp.screen.listmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sun.mvp.R
import com.sun.mvp.data.model.Movie
import com.sun.mvp.utils.OnItemRecyclerViewClickListener
import com.sun.mvp.utils.ext.loadImageCircleWithUrl
import com.sun.mvp.utils.ext.notNull

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder?>() {

    private val movies = mutableListOf<Movie>()
    private var onItemClickListener: OnItemRecyclerViewClickListener<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_movie, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun registerItemRecyclerViewClickListener(
        onItemRecyclerViewClickListener: OnItemRecyclerViewClickListener<Movie>?
    ) {
        onItemClickListener = onItemRecyclerViewClickListener
    }

    fun updateData(movies: MutableList<Movie>?) {
        movies.notNull {
            this.movies.clear()
            this.movies.addAll(it)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(
        itemView: View,
        private val itemClickListener: OnItemRecyclerViewClickListener<Movie>?
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var mTextViewTitle: TextView? = null
        private var mTextViewRatting: TextView? = null
        private var mTextViewContent: TextView? = null
        private var mImageViewIconMovie: ImageView? = null

        private var movieData: Movie? = null
        private var listener: OnItemRecyclerViewClickListener<Movie>? = null

        init {
            mTextViewTitle = itemView.findViewById(R.id.textViewTitle)
            mTextViewRatting = itemView.findViewById(R.id.textViewRatting)
            mTextViewContent = itemView.findViewById(R.id.textViewContent)
            mImageViewIconMovie = itemView.findViewById(R.id.imageMovie)
            itemView.setOnClickListener(this)
            listener = itemClickListener
        }

        fun bindViewData(movie: Movie) {
            movie.let {
                mTextViewTitle?.text = it.title
                mTextViewRatting?.text = it.vote.toString()
                mTextViewContent?.text = it.originalTitle
                mImageViewIconMovie?.loadImageCircleWithUrl(it.urlImage)
                movieData = it
            }
        }

        override fun onClick(view: View?) {
            listener?.onItemClick(movieData)
        }
    }
}