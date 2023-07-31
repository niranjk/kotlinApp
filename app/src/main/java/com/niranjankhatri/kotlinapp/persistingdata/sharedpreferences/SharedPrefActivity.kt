package com.niranjankhatri.kotlinapp.persistingdata.sharedpreferences

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.databinding.ActivitySharedPrefBinding
import com.niranjankhatri.kotlinapp.notes.NotesApplication
import com.niranjankhatri.kotlinapp.persistingdata.files.PICK_FILE_REQUEST_CODE

class SharedPrefActivity : AppCompatActivity() {

    lateinit var binding : ActivitySharedPrefBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPrefBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateView()
    }

    private fun updateView(){
        val prefWrapper = (application as NotesApplication).preferenceWrapper
        val prefViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PrefenceViewModel(prefWrapper) as T
            }
        }).get(PrefenceViewModel::class.java)

        prefViewModel.getText().observe(this){
            // updating the view from your shared prefs
            binding.activityMainTextView.text = it
        }

        binding.activityMainButton.setOnClickListener {
            // Saving to your Shared Prefs
            prefViewModel.saveText(binding.activityMainEditText.text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == Activity.RESULT_OK ){
            data?.data?.let { uri ->
                // the system will give you a URI that grants you temporary
                // permission to that file, allowing you to read and write
                // handle the selected file URI
            }
        }
    }
}