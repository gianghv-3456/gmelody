package com.sun.gmelody.screen.tracklist

import android.view.LayoutInflater
import android.widget.Toast
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.track.TrackRepositoryImpl
import com.sun.gmelody.data.repository.track.source.local.TrackLocalDataSource
import com.sun.gmelody.databinding.FragmentTracksBinding
import com.sun.gmelody.screen.tracklist.adapter.TracksAdapter
import com.sun.gmelody.utils.OnItemRecyclerViewClickListener
import com.sun.gmelody.utils.base.BaseFragment
import java.lang.ref.WeakReference

class TracksFragment :
    BaseFragment<FragmentTracksBinding>(),
    TracksContract.View,
    OnItemRecyclerViewClickListener<Track> {
    private lateinit var mTracksPresenter: TracksPresenter
    private val mTrackAdapter: TracksAdapter by lazy { TracksAdapter() }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentTracksBinding {
        return FragmentTracksBinding.inflate(inflater)
    }

    override fun onGetTrackListSuccess(tracks: MutableList<Track>) {
        mTrackAdapter.updateData(tracks)
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(item: Track?) {
//        addFragment(R.id.layoutContainer, DetailFragment.newInstance(item), true)
    }

    override fun initData() {
        mTracksPresenter =
            TracksPresenter(
                TrackRepositoryImpl.getInstance(
                    localSource = TrackLocalDataSource.getInstance(),
                    WeakReference(context),
                ),
            )
        mTracksPresenter.setView(this)
        mTracksPresenter.getTrackList()
    }

    override fun initView() {
        viewBinding.recyclerViewTrack.apply {
            this.adapter = mTrackAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TracksFragment()
    }
}
