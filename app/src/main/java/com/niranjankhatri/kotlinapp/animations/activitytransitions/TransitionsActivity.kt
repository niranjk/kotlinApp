package com.niranjankhatri.kotlinapp.animations.activitytransitions

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.databinding.ActivityTransitionsBinding

class TransitionsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTransitionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /** Doing Activity Transition via code in Activity
        window.apply {
        requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        enterTransition = Explode()
        exitTransition = Slide()
        }*/
        binding = ActivityTransitionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateView()
    }

    private fun updateView(){
        with(binding){
            computeButton.setOnClickListener {
                val amount = amountText.text.toString().ifBlank { "0" }
                val percent = percentText.text.toString().ifBlank { "0" }
                val intent = Intent(this@TransitionsActivity, OutputActivity::class.java).apply {
                    putExtra("amount", amount)
                    putExtra("percent", percent)
                }
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@TransitionsActivity).toBundle()
                )
            }
        }
    }
}