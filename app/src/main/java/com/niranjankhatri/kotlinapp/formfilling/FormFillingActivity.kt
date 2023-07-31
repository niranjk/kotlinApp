package com.niranjankhatri.kotlinapp.formfilling

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.niranjankhatri.kotlinapp.R

class FormFillingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        findViewById<Button>(R.id.submit_btn)?.setOnClickListener {
            // we get the greeting diplay text here
            val greetingDisplay = findViewById<TextView>(R.id.greeting_tv)
            // we will get the first name fromt he TextInputEditText value
            val firstName = findViewById<TextInputEditText>(R.id.first_name)?.text.toString().trim()
            val lastName = findViewById<TextInputEditText>(R.id.last_name)?.text.toString().trim()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()){
                val displayName = firstName.plus(" ").plus(lastName)
                greetingDisplay?.text = "${getString(R.string.welcome_text)} $displayName"
            }else {
                Toast.makeText(
                    this,
                    getString(R.string.enter_text_msg),
                    Toast.LENGTH_LONG
                ).apply { setGravity(Gravity.CENTER, 0,0) }.show()
            }
        }
    }
}