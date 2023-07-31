package com.niranjankhatri.kotlinapp.testing.integration.robolectric

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.niranjankhatri.kotlinapp.R

class RobolectricActivity : AppCompatActivity() {

    private lateinit var textFormatter: TextFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_robolectric)
        textFormatter = TextFormatter(FactorialGenerator(), applicationContext)
        findViewById<Button>(R.id.rb_button).setOnClickListener {
            findViewById<TextView>(R.id.rb_textView).text = textFormatter.getFactorialResult(
                findViewById<EditText>(R.id.rb_editText).text.toString().toInt()
            )
        }
    }
}