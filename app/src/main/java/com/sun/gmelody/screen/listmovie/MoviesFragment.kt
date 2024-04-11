package com.sun.gmelody.screen.listmovie

import android.view.LayoutInflater
import android.widget.Toast
import com.sun.gmelody.R
import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.data.repository.MovieRepository
import com.sun.gmelody.data.repository.source.local.MovieLocalDataSource
import com.sun.gmelody.data.repository.source.remote.MovieRemoteDataSource
import com.sun.gmelody.databinding.FragmentMoviesBinding
import com.sun.gmelody.screen.detail.DetailFragment
import com.sun.gmelody.screen.listmovie.adapter.MoviesAdapter
import com.sun.gmelody.utils.OnItemRecyclerViewClickListener
import com.sun.gmelody.utils.base.BaseFragment
import com.sun.gmelody.utils.ext.addFragment
import java.lang.Exception

class MoviesFragment :
    BaseFragment<FragmentMoviesBinding>(),
    MoviesContract.View,
    OnItemRecyclerViewClickListener<Movie> {

    private lateinit var mMoviesPresenter: MoviesPresenter
    private val mMovieAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentMoviesBinding {
        return FragmentMoviesBinding.inflate(inflater)
    }

    override fun initView() {
        viewBinding.recyclerViewMovie.apply {
            adapter = mMovieAdapter
        }
        mMovieAdapter.registerItemRecyclerViewClickListener(this)
    }

    override fun initData() {
        mMoviesPresenter = MoviesPresenter(
            MovieRepository.getInstance(
                MovieRemoteDataSource.getInstance(),
                MovieLocalDataSource.getInstance()
            )
        )
        mMoviesPresenter.setView(this)
        mMoviesPresenter.getMovies()
    }

    override fun onGetMoviesSuccess(movies: MutableList<Movie>) {
        mMovieAdapter.updateData(movies)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: Movie?) {
        addFragment(R.id.layoutContainer, DetailFragment.newInstance(item), true)
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
