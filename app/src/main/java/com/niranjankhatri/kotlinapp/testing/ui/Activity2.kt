package com.niranjankhatri.kotlinapp.testing.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.niranjankhatri.kotlinapp.R

class Activity2 : AppCompatActivity() {
    companion object{
        private const val EXTRA_SECONDS = "extra_seconds"
        fun newIntent(context: Context, seconds: Int) =
            Intent(context, Activity2::class.java).putExtra(EXTRA_SECONDS, seconds)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        findViewById<TextView>(R.id.activity_2_text_view).text =
            getString(R.string.opened_after_x_seconds, intent.getIntExtra(EXTRA_SECONDS, 0))
    }
}