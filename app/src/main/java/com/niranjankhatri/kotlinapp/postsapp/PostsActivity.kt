package com.niranjankhatri.kotlinapp.postsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.niranjankhatri.kotlinapp.databinding.ActivityPostsBinding
import dagger.hilt.android.AndroidEntryPoint
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class PostsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostsBinding

    private val postsViewModel: PostsViewModel by viewModels()
    private lateinit var postsAdapter: PostsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsAdapter = PostsAdapter(LayoutInflater.from(this))
        with(binding){
            activityMainRecyclerView.adapter = postsAdapter
            activityMainRecyclerView.layoutManager = LinearLayoutManager(this@PostsActivity)
        }
        postsViewModel.getPosts().observe(this){
            postsAdapter.updatePosts(it)
        }
    }
}