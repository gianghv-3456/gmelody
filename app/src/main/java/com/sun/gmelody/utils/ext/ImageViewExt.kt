package com.sun.gmelody.utils.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sun.gmelody.utils.Constant

fun ImageView.loadImageCircleWithUrl(url: String) {
    Glide.with(this)
        .load(Constant.BASE_URL_IMAGE + url)
        .circleCrop()
        .into(this)
}

fun ImageView.loadImageWithUrl(url: String) {
    Glide.with(this)
        .load(Constant.BASE_URL_IMAGE + url)
        .into(this)
}
