package com.niranjankhatri.kotlinapp.postsapp.repository

import androidx.lifecycle.LiveData
import com.niranjankhatri.kotlinapp.postsapp.api.Post

interface PostRepository {
    fun getPosts(): LiveData<List<Post>>
}