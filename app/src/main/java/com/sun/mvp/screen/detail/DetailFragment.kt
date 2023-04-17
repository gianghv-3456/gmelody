package com.sun.mvp.screen.detail

import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import com.sun.mvp.data.model.Movie
import com.sun.mvp.databinding.FragmentDetailBinding
import com.sun.mvp.utils.base.BaseFragment
import com.sun.mvp.utils.ext.goBackFragment
import com.sun.mvp.utils.ext.loadImageCircleWithUrl
import com.sun.mvp.utils.ext.loadImageWithUrl
import com.sun.mvp.utils.ext.notNull

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private var mMovie: Movie? = null

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater)
    }

    override fun initData() {
        arguments?.run {
            mMovie = getParcelable(ARGUMENT_MOVIE)
        }
        mMovie?.notNull {
            viewBinding.apply {
                imageBackDrop.loadImageWithUrl(it.backDropImage)
                imageMovie.loadImageCircleWithUrl(it.urlImage)
                textTitle.text = it.title
                textDescription.text = it.overView
                textRatting.text = it.vote.toString()
                textTotalReview.text = it.voteCount.toString()
            }
        }
    }

    override fun initView() {
        viewBinding.buttonImageBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                goBackFragment()
            }
        })
    }

    companion object {
        private const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        fun newInstance(movie: Movie?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_MOVIE to movie)
        }
    }
}
