package com.sun.gmelody.screen.tracklist

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import android.view.LayoutInflater
import android.widget.Toast
import com.sun.gmelody.data.model.Track
import com.sun.gmelody.data.repository.track.TrackRepositoryImpl
import com.sun.gmelody.data.repository.track.source.local.TrackLocalDataSource
import com.sun.gmelody.databinding.FragmentTracksBinding
import com.sun.gmelody.screen.tracklist.adapter.TracksAdapter
import com.sun.gmelody.service.MusicService
import com.sun.gmelody.service.MusicService.MusicBinder
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
        val intent = Intent(context, MusicService::class.java)
        activity?.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDetach() {
        if (isBound) {
            activity?.unbindService(serviceConnection)
            isBound = false
        }
        super.onDetach()
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

    private val serviceConnection: ServiceConnection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName,
                service: IBinder,
            ) {
                val binder = service as MusicBinder
                musicService = binder.service
                isBound = true
                // Start updating seek bar
                updateSeekBar()
            }

            override fun onServiceDisconnected(name: ComponentName) {
                isBound = false
            }
        }

    private fun updateSeekBar() {
        // Update seek bar every second
        Handler().postDelayed(
            Runnable {
                if (isBound) {
                    val currentPosition = musicService!!.getCurrentPosition()
                    viewBinding.songProgressBar.progress = currentPosition
                    // Recursive call to continue updating seek bar
                    updateSeekBar()
                }
            },
            1000,
        ) // 1 second interval
    }

    private var musicService: MusicService? = null
    private var isBound = false
}
