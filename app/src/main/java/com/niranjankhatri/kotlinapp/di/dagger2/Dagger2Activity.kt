package com.niranjankhatri.kotlinapp.di.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.databinding.ActivityDagger2Binding
import javax.inject.Inject

class Dagger2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDagger2Binding

    @Inject
    lateinit var factory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDagger2Binding.inflate(layoutInflater)
        (application as Dagger2ApplicationClass).applicationComponent.createMainSubcomponent().inject(this)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this, factory).get(MyNumberViewModel::class.java)
        viewModel.numberLiveData.observe(this){
            binding.activityMainTextView.text = it.toString()
        }

        binding.activityMainButton.setOnClickListener {
            viewModel.generateNextNumber()
        }
    }

}