package com.niranjankhatri.kotlinapp.animations.motionlayout

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.animations.activitytransitions.OutputActivity
import com.niranjankhatri.kotlinapp.databinding.ActivityMotionBinding
import com.niranjankhatri.kotlinapp.databinding.ActivityOutputBinding
import com.niranjankhatri.kotlinapp.databinding.ActivityTransitionsBinding
import java.math.BigDecimal

class MotionActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateView()
    }

    private fun updateView(){
        with(binding){
            computeButton.setOnClickListener {
                val amount = amountText.text.toString().ifBlank { "0" }
                val percent = percentText.text.toString().ifBlank { "0" }
                val intent = Intent(this@MotionActivity, MotionOutputActivity::class.java).apply {
                    putExtra("amount", amount)
                    putExtra("percent", percent)
                }

                val buttonPair: android.util.Pair<View, String> = android.util.Pair(binding.computeButton, "button")
                val imagePair: android.util.Pair<View, String> = android.util.Pair(binding.image, "transition_name")
                startActivity(
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(this@MotionActivity,binding.image, "transition_name").toBundle()
                )
            }
        }
    }
}