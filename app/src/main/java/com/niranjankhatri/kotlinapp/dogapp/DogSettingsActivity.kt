package com.niranjankhatri.kotlinapp.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.niranjankhatri.kotlinapp.databinding.ActivityDogSettingsBinding

class DogSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activitySettingsButton.setOnClickListener {
            (application as DogApplication).preferenceWrapper.saveNumberOfResults(
                binding.activitySettingsEditText.text.toString().toIntOrNull() ?: 0
            )
        }
    }
}