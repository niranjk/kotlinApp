package com.niranjankhatri.kotlinapp.intent.retrieveresult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R

class RainbowColorPickerActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rainbow_color_picker)
        val colorPickerClickListener = View.OnClickListener {
            view ->
            when(view.id){
                R.id.red_btn -> setRainbowColor(getString(R.string.retrieve_result_red_txt), R.color.red)
                R.id.orange_btn -> setRainbowColor(getString(R.string.retrieve_result_orange_txt), R.color.orange)
                R.id.yellow_btn -> setRainbowColor(getString(R.string.retrieve_result_yellow_txt), R.color.yellow)
                R.id.green_btn -> setRainbowColor(getString(R.string.retrieve_result_green_txt), R.color.green)
                R.id.blue_btn -> setRainbowColor(getString(R.string.retrieve_result_blue_txt), R.color.blue)
                R.id.indigo_btn -> setRainbowColor(getString(R.string.retrieve_result_indigo_txt), R.color.indigo)
                R.id.violet_btn -> setRainbowColor(getString(R.string.retrieve_result_violet_txt), R.color.violet)
            }
        }

        findViewById<Button>(R.id.red_btn).setOnClickListener(colorPickerClickListener)
        findViewById<Button>(R.id.orange_btn).setOnClickListener(colorPickerClickListener)
        findViewById<Button>(R.id.yellow_btn).setOnClickListener(colorPickerClickListener)
        findViewById<Button>(R.id.green_btn).setOnClickListener(colorPickerClickListener)
        findViewById<Button>(R.id.blue_btn).setOnClickListener(colorPickerClickListener)
        findViewById<Button>(R.id.indigo_btn).setOnClickListener(colorPickerClickListener)
        findViewById<Button>(R.id.violet_btn).setOnClickListener(colorPickerClickListener)
    }

    private fun setRainbowColor(colorName: String, color: Int){
        Intent().let { pickedColorIntent ->
            pickedColorIntent.putExtra(AppConstants.RAINBOW_COLOR_NAME, colorName)
            pickedColorIntent.putExtra(AppConstants.RAINBOW_COLOR, color)
            setResult(Activity.RESULT_OK, pickedColorIntent)
            finish()
        }
    }

}