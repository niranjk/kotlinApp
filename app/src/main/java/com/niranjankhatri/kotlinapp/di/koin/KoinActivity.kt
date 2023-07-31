package com.niranjankhatri.kotlinapp.di.koin

import android.os.Bundle
import com.niranjankhatri.kotlinapp.databinding.ActivityKoinBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity

class KoinActivity : ScopeActivity() {

    private lateinit var binding: ActivityKoinBinding
    private val koinViewModel : KoinViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        koinViewModel.numberLiveData.observe(this){
            binding.activityMainTextView.text = it.toString()
        }
        binding.activityMainButton.setOnClickListener {
            koinViewModel.generateNextNumber()
        }
    }
}