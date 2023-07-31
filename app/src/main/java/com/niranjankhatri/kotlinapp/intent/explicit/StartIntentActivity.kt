package com.niranjankhatri.kotlinapp.intent.explicit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R

class StartIntentActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_intent)
        findViewById<Button>(R.id.intent_submit_btn).setOnClickListener {
            val fullName = findViewById<EditText>(R.id.intent_fullname_et).text.toString()
            if (fullName.isNotEmpty()){
                // set the name of the Activity to launch
                val welcomeIntent = Intent(this, WelcomeActivity::class.java)
                // pass the value to the WelcomeActivity
                welcomeIntent.putExtra(AppConstants.FULL_NAME_KEY, fullName)
                startActivity(welcomeIntent)
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.intent_app_enter_fullname_txt),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}