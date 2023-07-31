package com.niranjankhatri.kotlinapp.postsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niranjankhatri.kotlinapp.postsapp.api.Post
import com.niranjankhatri.kotlinapp.postsapp.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel(){

    fun getPosts(): LiveData<List<Post>> = postRepository.getPosts()
}