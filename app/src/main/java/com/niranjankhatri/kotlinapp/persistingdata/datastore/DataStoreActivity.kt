package com.niranjankhatri.kotlinapp.persistingdata.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.databinding.ActivityDataStoreBinding
import com.niranjankhatri.kotlinapp.notes.NotesApplication

class DataStoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateView()
    }

    fun updateView(){
        val settingsStore = (application as NotesApplication).settingsStore

        val viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(settingsStore) as T
            }
        }).get(SettingsViewModel::class.java)

        viewModel.textLiveData.observe(this){
            binding.activityMainTextView.text = it
        }

        binding.activityMainButton.setOnClickListener {
            viewModel.saveText(binding.activityMainEditText.text.toString())
        }
    }
}