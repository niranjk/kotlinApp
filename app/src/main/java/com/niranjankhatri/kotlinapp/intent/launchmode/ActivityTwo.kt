package com.niranjankhatri.kotlinapp.intent.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.R

class ActivityTwo : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        Log.d(TAG, "onCreate")
        val buttonClickListener = View.OnClickListener { view ->

            when (view.id) {
                R.id.number1 -> startActivity(Intent(this, ActivityOne::class.java))
                R.id.number2 -> startActivity(Intent(this, ActivityTwo::class.java))
                R.id.number3 -> startActivity(Intent(this, ActivityThree::class.java))
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.unexpected_button_pressed),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        }

        findViewById<View>(R.id.number1).setOnClickListener(buttonClickListener)
        findViewById<View>(R.id.number2).setOnClickListener(buttonClickListener)
        findViewById<View>(R.id.number3).setOnClickListener(buttonClickListener)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent")
    }

    companion object {
        private const val TAG = "ActivityTwo"
    }

}