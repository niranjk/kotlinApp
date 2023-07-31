package com.niranjankhatri.kotlinapp.dogapp.api

import com.squareup.moshi.Json

data class Dog(
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "message")
    val urls: List<String>
)
