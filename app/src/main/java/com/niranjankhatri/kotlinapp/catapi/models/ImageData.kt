package com.niranjankhatri.kotlinapp.catapi.models

import com.squareup.moshi.Json

data class ImageData(
    @field:Json(name = "url") val imageUrl : String
)
