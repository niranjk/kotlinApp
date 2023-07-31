package com.niranjankhatri.kotlinapp.di.dagger2

import android.app.Application

class Dagger2ApplicationClass : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()
    }
}