package com.niranjankhatri.kotlinapp.postsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.niranjankhatri.kotlinapp.postsapp.api.Post
import com.niranjankhatri.kotlinapp.postsapp.api.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepositoryImpl(
    private val postService: PostService
) : PostRepository {

    override fun getPosts(): LiveData<List<Post>> {
        val result = MutableLiveData<List<Post>>()
        postService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    result.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                // error handling later ..
            }

        })
        return result
    }

}