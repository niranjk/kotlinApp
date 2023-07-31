package com.niranjankhatri.kotlinapp.postsapp.api

import com.squareup.moshi.Json

data class Post(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "userId")
    val userId: Long,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "body")
    val body: String
)
