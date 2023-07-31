package com.niranjankhatri.kotlinapp.intent.explicit

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.AppConstants
import com.niranjankhatri.kotlinapp.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        if (intent != null){
            val fullName = intent.getStringExtra(AppConstants.FULL_NAME_KEY)
            findViewById<TextView>(R.id.welcome_text).text = getString(R.string.intent_app_welcome_txt, fullName)
        }
    }
}