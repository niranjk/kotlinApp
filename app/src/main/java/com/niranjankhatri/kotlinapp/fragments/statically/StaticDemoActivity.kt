package com.niranjankhatri.kotlinapp.fragments.statically

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.R

class StaticDemoActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_static)
    }
}