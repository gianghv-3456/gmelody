package com.sun.gmelody.utils.music

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import com.sun.gmelody.data.model.Track

object MediaGetter {
    fun musicProjection() =
        arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
        )

    fun queryLocalMusic(context: Context): MutableList<Track>? {
        val musicList = mutableListOf<Track>()

        val contentResolver: ContentResolver = context.contentResolver
        val uri = MediaUri.getAudioUri()
        val projection = musicProjection()
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, sortOrder)
        cursor?.columnNames?.forEach { Log.d("CURSOR", it) }

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val displayNameColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val dataColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val title = it.getString(titleColumn)
                val artist = it.getString(artistColumn)
                val displayName = it.getString(displayNameColumn)
                val duration = it.getLong(durationColumn)
                val data = it.getString(dataColumn)

                val track = Track(id, title, displayName, artist, duration, data)
                musicList.add(track)

                Log.d("TRACK_GOT", "$track")
            }
        }

        cursor?.close()
        if (musicList.isEmpty()) {
            return null
        }
        return musicList
    }
}
