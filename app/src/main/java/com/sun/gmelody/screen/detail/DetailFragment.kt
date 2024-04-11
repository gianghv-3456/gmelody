package com.sun.gmelody.screen.detail

import android.view.LayoutInflater
import androidx.core.os.bundleOf
import com.sun.gmelody.data.model.Movie
import com.sun.gmelody.databinding.FragmentDetailBinding
import com.sun.gmelody.utils.base.BaseFragment
import com.sun.gmelody.utils.ext.goBackFragment
import com.sun.gmelody.utils.ext.loadImageCircleWithUrl
import com.sun.gmelody.utils.ext.loadImageWithUrl
import com.sun.gmelody.utils.ext.notNull

@Suppress("DEPRECATION")
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
        viewBinding.buttonImageBack.setOnClickListener { goBackFragment() }
    }

    companion object {
        private const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        fun newInstance(movie: Movie?) = DetailFragment().apply {
            arguments = bundleOf(ARGUMENT_MOVIE to movie)
        }
    }
}
