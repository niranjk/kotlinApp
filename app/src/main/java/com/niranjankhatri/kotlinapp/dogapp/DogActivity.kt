package com.niranjankhatri.kotlinapp.dogapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.ActivityDogBinding
import com.niranjankhatri.kotlinapp.dogapp.viewmodel.DogViewModel

class DogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogBinding
    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogViewModel : DogViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val downloadRepository = (application as DogApplication).downloadRepository
        dogViewModel = ViewModelProvider(this,
                object: ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return DogViewModel(downloadRepository) as T
                    }
                }
            )[DogViewModel::class.java]

        dogViewModel.downloadResult.observe(this){ result ->
            when(result){
                is com.niranjankhatri.kotlinapp.dogapp.repository.Result.Loading -> {
                    binding.activityMainProgressBar.visibility = View.VISIBLE
                }
                is com.niranjankhatri.kotlinapp.dogapp.repository.Result.Success -> {
                    binding.activityMainProgressBar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Success!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is com.niranjankhatri.kotlinapp.dogapp.repository.Result.Error -> {
                    binding.activityMainProgressBar.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Error!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        with(binding){
            activityMainRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
            dogAdapter = DogAdapter(LayoutInflater.from(applicationContext)){
                dogViewModel.downloadFile(it.url)
            }
            activityMainRecyclerView.adapter = dogAdapter
            dogViewModel.dogsLiveData.observe(this@DogActivity){
                when(it){
                    is com.niranjankhatri.kotlinapp.dogapp.repository.Result.Success -> {
                        dogAdapter.updateDogs(it.data)
                    }
                    else -> {
                        // nothing
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dogViewModel.getDogs()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, DogSettingsActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}