package com.niranjankhatri.kotlinapp.animations.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.ActivityMotionOutputBinding
import com.niranjankhatri.kotlinapp.databinding.ActivityOutputBinding
import java.math.BigDecimal

class MotionOutputActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMotionOutputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionOutputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateView()
    }

    private fun updateView(){
        val amount = intent?.getStringExtra("amount")?.toBigDecimal() ?: BigDecimal.ZERO
        val percent = intent?.getStringExtra("percent")?.toBigDecimal() ?: BigDecimal.ZERO
        val tip = amount * (percent.divide("100".toBigDecimal()))
        binding.tipText.text = "Motion Layout "+getString(R.string.tip, tip)
    }
}