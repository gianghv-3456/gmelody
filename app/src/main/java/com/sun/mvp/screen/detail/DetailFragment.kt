package com.sun.mvp.screen.detail

import android.view.View
import androidx.core.os.bundleOf
import com.sun.mvp.R
import com.sun.mvp.data.model.Movie
import com.sun.mvp.utils.base.BaseFragment
import com.sun.mvp.utils.ext.goBackFragment
import com.sun.mvp.utils.ext.loadImageCircleWithUrl
import com.sun.mvp.utils.ext.loadImageWithUrl
import com.sun.mvp.utils.ext.notNull
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.imageMovie
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : BaseFragment() {

    private var mMovie: Movie? = null

    override fun getLayoutResourceId() = R.layout.fragment_detail


    override fun initView(view: View) {
        view.buttonImageBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                goBackFragment()
            }
        })
    }

    override fun initData() {
        arguments?.run {
            mMovie = getParcelable(ARGUMENT_MOVIE)
        }
        mMovie?.notNull {
            imageBackDrop.loadImageWithUrl(it.backDropImage)
            imageMovie.loadImageCircleWithUrl(it.urlImage)
            textTitle.text = it.title
            textDescription.text = it.overView
            textRatting.text = it.vote.toString()
            textTotalReview.text = it.voteCount.toString()
        }
    }

    companion object {
        private const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        fun newInstance(movie: Movie?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_MOVIE to movie)
        }
    }
}
