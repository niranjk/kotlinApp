package com.niranjankhatri.kotlinapp.di.manual

import android.app.Application
import com.niranjankhatri.kotlinapp.di.manual.container.ApplicationContainer

class RandomApplicationClass : Application() {

    val applicationContainer = ApplicationContainer()

    override fun onCreate() {
        super.onCreate()
    }
}