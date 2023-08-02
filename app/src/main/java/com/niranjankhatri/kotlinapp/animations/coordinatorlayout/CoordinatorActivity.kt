package com.niranjankhatri.kotlinapp.animations.coordinatorlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.material.Snackbar
import com.google.android.material.snackbar.Snackbar
import com.niranjankhatri.kotlinapp.databinding.ActivityCoordinatorBinding

class CoordinatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Snackbar.make(binding.fab, " This is my snackbar message for this tutorial.", Snackbar.LENGTH_LONG).show()
    }
}