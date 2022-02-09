package com.sun.mvp.screen.listmovie

import android.view.View
import android.widget.Toast
import com.sun.mvp.R
import com.sun.mvp.data.model.Movie
import com.sun.mvp.data.repository.MovieRepository
import com.sun.mvp.data.repository.source.local.MovieLocalDataSource
import com.sun.mvp.data.repository.source.remote.MovieRemoteDataSource
import com.sun.mvp.screen.listmovie.adapter.MoviesAdapter
import com.sun.mvp.utils.OnItemRecyclerViewClickListener
import com.sun.mvp.utils.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.view.*
import java.lang.Exception

class MoviesFragment : BaseFragment(), MoviesContract.View, OnItemRecyclerViewClickListener<Movie> {

    private lateinit var mMoviesPresenter: MoviesPresenter
    private val mMovieAdapter: MoviesAdapter by lazy { MoviesAdapter() }

    override fun getLayoutResourceId() = R.layout.fragment_movies

    override fun initView(view: View) {
        view.recyclerViewMovie.apply {
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
        Toast.makeText(context,exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: Movie?) {
        Toast.makeText(context,item?.title, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }
}
