package com.niranjankhatri.kotlinapp.catapi

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView)
}