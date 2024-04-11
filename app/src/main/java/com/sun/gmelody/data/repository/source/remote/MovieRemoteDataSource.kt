package com.sun.gmelody.data.repository.source.remote

import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.data.model.MovieEntry
import com.sun.gmelody.data.repository.source.MovieDataSource
import com.sun.gmelody.data.repository.source.remote.fetchjson.GetJsonFromUrl
import com.sun.gmelody.utils.Constant

class MovieRemoteDataSource : MovieDataSource.Remote {
    override fun getMovies(listener: OnResultListener<MutableList<Movie>>) {
        GetJsonFromUrl(
            urlString = Constant.BASE_URL + Constant.BASE_PAGE,
            keyEntity = MovieEntry.MOVIES,
            listener = listener
        )
    }

    companion object {
        private var instance: MovieRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: MovieRemoteDataSource().also { instance = it }
        }
    }
}
