package com.niranjankhatri.kotlinapp.intent.launchmode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.R

class LaunchModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launchmode)
        Log.d(TAG, "onCreate")
        val buttonClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.letterA -> startActivity(Intent(this, ActivityA::class.java))
                R.id.letterB -> startActivity(Intent(this, ActivityB::class.java))
                R.id.letterC -> startActivity(Intent(this, ActivityC::class.java))
                R.id.number1 -> startActivity(Intent(this, ActivityOne::class.java))
                R.id.number2 -> startActivity(Intent(this, ActivityTwo::class.java))
                R.id.number3 -> startActivity(Intent(this, ActivityThree::class.java))
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.unexpected_button_pressed),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        findViewById<Button>(R.id.letterA).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.letterB).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.letterC).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.number1).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.number2).setOnClickListener(buttonClickListener)
        findViewById<Button>(R.id.number3).setOnClickListener(buttonClickListener)

    }

    companion object {
        private const val TAG = "LaunchModeActivity"
    }
}