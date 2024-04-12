package com.sun.gmelody.screen

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.sun.gmelody.R
import com.sun.gmelody.screen.splash.SplashFragment
import com.sun.gmelody.utils.base.BaseActivity
import com.sun.gmelody.utils.permission.PermissionAsker
import java.io.File

class MainActivity : BaseActivity() {
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
//        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
//
//        val projection = arrayOf(
//            MediaStore.Audio.Media._ID,
//            MediaStore.Audio.Media.ARTIST,
//            MediaStore.Audio.Media.TITLE,
//            MediaStore.Audio.Media.DATA,
//            MediaStore.Audio.Media.DISPLAY_NAME,
//            MediaStore.Audio.Media.DURATION
//        )
//
//        Log.d("HAHA","Prepare to query media")
//        val cursor = managedQuery(
//            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, null, null
//        )
//
//        Log.d("HAHA","Cursor count: ${cursor.count}")
//        cursor.count
//
//        val songs: MutableList<String> = ArrayList()
//        while (cursor.moveToNext()) {
//            songs.add(
//                ((((cursor.getString(0) + "||" + cursor.getString(1)) + "||" + cursor.getString(2)) + "||" + cursor.getString(
//                    3
//                )) + "||" + cursor.getString(4)) + "||" + cursor.getString(5)
//            )
//        }
//
//        songs.forEach { Log.d("HAHA_SONG", it) }

// //        val list = getMusicFiles(this)`
//        val list = getPlayList(Environment.getExternalStorageDirectory().absolutePath)
//        Log.d("HAHA_MUSIC", "List: ${list?.size}")
//
//        list?.forEach {
//            Log.d("HAHA_M", it.toString())
//            val info = getMusicFileInfo(it["file_path"].toString())
//            info.forEach { Log.d("M_INFO", "${it.key}:\t${it.value}") }
//        }
//
//        getMusicFiles(this)

        supportFragmentManager.beginTransaction().replace(R.id.layoutContainer, SplashFragment.newInstance())
            .disallowAddToBackStack().commit()

//        val musicPlayer = MediaPlayer.create(this, Uri.fromFile(File(list?.get(0)?.get("file_path"))))
//        musicPlayer.start()
    }

    fun getMusicFiles(context: Context): List<String> {
        val musicList = mutableListOf<String>()
        val contentResolver: ContentResolver = context.contentResolver
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection =
            arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
            )

        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, sortOrder)
        Log.d("TAG_EXTERNAL_QUERY", cursor?.count.toString())

        val cursor1: Cursor? =
            contentResolver.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, projection, null, null, sortOrder)
        Log.d("TAG_INTERNAL_QUERY", cursor1?.count.toString())

        cursor1?.close()

        cursor?.columnNames?.forEach { Log.d("CURSOR", it) }
        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = it.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val title = it.getString(titleColumn)
                val artist = it.getString(artistColumn)
                musicList.add("ID: $id, Title: $title, Artist: $artist")
                Log.d("HAHA_CAY", "$id\t$title\t$artist")
            }
        }
        cursor?.close()

        return musicList
    }

    fun getMusicFileInfo(filePath: String): Map<String, String> {
        val retriever = MediaMetadataRetriever()
        try {
            retriever.setDataSource(filePath)
            retriever.embeddedPicture
            val title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            val duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) // Duration in ms
            val year = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)
            val genre = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)

            // You can add more metadata keys here

            return mapOf(
                "Title" to (title ?: "Unknown"),
                "Artist" to (artist ?: "Unknown"),
                "Album" to (album ?: "Unknown"),
                "Duration" to (duration?.let { formatDuration(it.toLong()) } ?: "Unknown"),
                "Year" to (year ?: "Unknown"),
                "Genre" to (genre ?: "Unknown"),
            )
        } finally {
            retriever.release() // Make sure to release the retriever
        }
    }

    fun formatDuration(durationMs: Long): String {
        val seconds = (durationMs / 1000) % 60
        val minutes = (durationMs / (1000 * 60)) % 60
        val hours = (durationMs / (1000 * 60 * 60)) % 24
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    fun getPlayList(rootPath: String?): ArrayList<HashMap<String, String>>? {
        val fileList = ArrayList<HashMap<String, String>>()
        return try {
            val rootFolder = File(rootPath)

            val files = rootFolder.listFiles() // here you will get NPE if directory doesn't contains
            // any file,handle it like this.
            for (file in files) {
                if (file.isDirectory) {
                    if (getPlayList(file.absolutePath) != null) {
                        fileList.addAll(getPlayList(file.absolutePath)!!)
                    } else {
                        break
                    }
                } else if (file.name.endsWith(".mp3")) {
                    val song = HashMap<String, String>()
                    song["file_path"] = file.absolutePath
                    song["file_name"] = file.name
                    fileList.add(song)
                }
            }
            fileList
        } catch (e: Exception) {
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun requestPermission() {
        if (!PermissionAsker.checkExternalStoragePermission(this) || !PermissionAsker.checkAudioStorePermission(this)) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_MEDIA_AUDIO),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE,
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1101
    }
}
