package com.niranjankhatri.kotlinapp.di.manual

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.databinding.ActivityManualDiactivityBinding
import com.niranjankhatri.kotlinapp.di.manual.container.RandomContainer

class ManualDIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManualDiactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualDiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val randomContainer = RandomContainer(
            (application as RandomApplicationClass).applicationContainer.numberRepository
        )
        val viewModel = ViewModelProvider(this,
            randomContainer.getManualViewModelFactory()
        ).get(ManualViewModel::class.java)

        viewModel.numberLiveData.observe(this){
            binding.activityMainTextView.text = it.toString()
        }

        binding.activityMainButton.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }

}