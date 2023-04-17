package com.sun.mvp.screen.listmovie

import android.view.LayoutInflater
import android.widget.Toast
import com.sun.mvp.R
import com.sun.mvp.data.model.Movie
import com.sun.mvp.data.repository.MovieRepository
import com.sun.mvp.data.repository.source.local.MovieLocalDataSource
import com.sun.mvp.data.repository.source.remote.MovieRemoteDataSource
import com.sun.mvp.databinding.FragmentMoviesBinding
import com.sun.mvp.screen.detail.DetailFragment
import com.sun.mvp.screen.listmovie.adapter.MoviesAdapter
import com.sun.mvp.utils.OnItemRecyclerViewClickListener
import com.sun.mvp.utils.base.BaseFragment
import com.sun.mvp.utils.ext.addFragment
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
