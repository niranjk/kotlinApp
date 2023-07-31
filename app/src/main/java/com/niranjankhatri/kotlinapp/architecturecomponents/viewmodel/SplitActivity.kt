package com.niranjankhatri.kotlinapp.architecturecomponents.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.ActivitySplitBinding

class SplitActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}