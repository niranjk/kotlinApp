package com.niranjankhatri.kotlinapp.di.hilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.niranjankhatri.kotlinapp.databinding.ActivityHiltBinding
import com.niranjankhatri.kotlinapp.di.dagger2.ClassA
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HiltActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHiltBinding

    // simple injection
    // @Inject lateinit var myObject : ClassA

    private val viewModel : HiltViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiltBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.numberLiveData.observe(this){
            binding.activityMainTextView.text = it.toString()
        }

        binding.activityMainButton.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }
}