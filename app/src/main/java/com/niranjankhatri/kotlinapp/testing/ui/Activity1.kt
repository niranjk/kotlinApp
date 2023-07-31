package com.niranjankhatri.kotlinapp.testing.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.niranjankhatri.kotlinapp.MyApplication
import com.niranjankhatri.kotlinapp.R

class Activity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1)
        findViewById<Button>(R.id.activity_1_button).setOnClickListener {
            (application as MyApplication).synchonizer.executeAfterDelay {
                startActivity(Activity2.newIntent(this, it))
            }
        }
    }
}