package com.niranjankhatri.kotlinapp.animations.activitytransitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.databinding.ActivityOutputBinding
import java.math.BigDecimal

class OutputActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOutputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateView()
    }

    private fun updateView(){
        val amount = intent?.getStringExtra("amount")?.toBigDecimal() ?: BigDecimal.ZERO
        val percent = intent?.getStringExtra("percent")?.toBigDecimal() ?: BigDecimal.ZERO
        val tip = amount * (percent.divide("100".toBigDecimal()))
        binding.tipText.text = getString(R.string.tip, tip)
    }
}