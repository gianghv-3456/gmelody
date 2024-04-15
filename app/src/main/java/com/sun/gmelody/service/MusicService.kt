package com.sun.gmelody.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicService : Service() {
    private val mMediaPlayer: MediaPlayer by lazy { MediaPlayer() }

    override fun onBind(p0: Intent?): IBinder {
        return MusicBinder()
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun startPlayBack() {
        mMediaPlayer.start()
    }

    // Method to pause playback
    fun pausePlayback() {
        mMediaPlayer.pause()
    }

    // Method to get current playback position
    fun getCurrentPosition(): Int {
        return mMediaPlayer.getCurrentPosition()
    }

    inner class MusicBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }
}
