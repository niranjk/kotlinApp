package com.niranjankhatri.kotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.di.dagger2.ClassB
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ScopeActivity

class MainActivity : ScopeActivity() {

    val classB: ClassB by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/**
 *
 * 36*36 : ldpi (0.75 * baseline)
 * 48*48 : mdpi (1* baseline)
 * 72*72 : hdpi (1.5 * baseline)
 * 96*96 : xhdip (2 * baseline)
 * 144 * 144 : xxhdpi (3 * baseline)
 * 192 * 192 : xxxhdpi (4 * baseline)
 *
 *
 */




