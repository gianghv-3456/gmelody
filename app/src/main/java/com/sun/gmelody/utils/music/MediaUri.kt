package com.sun.gmelody.utils.music

import android.net.Uri
import android.provider.MediaStore

object MediaUri {
    fun getAudioUri(): Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
}
