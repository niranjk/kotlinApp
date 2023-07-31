package com.niranjankhatri.kotlinapp.intent.retrieveresult

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R

class RetrieveResultStartActivity : AppCompatActivity() {

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
            val data = activityResult.data
            val backgroundColor = data?.getIntExtra(AppConstants.RAINBOW_COLOR, Color.parseColor(AppConstants.DEFAULT_COLOR))
            val colorName = data?.getStringExtra(AppConstants.RAINBOW_COLOR_NAME) ?: ""
            val colorMessage = getString(R.string.retrieve_result_color_chosen_message_txt, colorName)

            val rainbowColor = findViewById<TextView>(R.id.retrieve_rainbowcolor)
            backgroundColor?.let {
                ContextCompat.getColor(this,
                    it
                )
            }?.let { rainbowColor.setBackgroundColor(it) }

            rainbowColor.text = colorMessage
            rainbowColor.isVisible = true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrieve_result)
        findViewById<Button>(R.id.retrieve_submit_btn).setOnClickListener {
            startActivityForResult.launch(Intent(this, RainbowColorPickerActivity::class.java))
        }
    }
}