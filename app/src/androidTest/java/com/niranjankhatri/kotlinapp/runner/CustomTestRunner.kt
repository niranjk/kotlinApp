package com.niranjankhatri.kotlinapp.runner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.niranjankhatri.kotlinapp.ui.TestMyApplication

class CustomTestRunner : AndroidJUnitRunner(){
    @Throws(Exception::class)
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, TestMyApplication::class.java.name, context)
    }
}