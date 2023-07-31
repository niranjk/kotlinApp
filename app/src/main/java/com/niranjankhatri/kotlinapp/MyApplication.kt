package com.niranjankhatri.kotlinapp

import android.app.Application
import com.niranjankhatri.kotlinapp.testing.ui.Randomizer
import com.niranjankhatri.kotlinapp.testing.ui.RandomizerImpl
import com.niranjankhatri.kotlinapp.testing.ui.Synchronizer
import com.niranjankhatri.kotlinapp.testing.ui.SynchronizerImpl
import java.util.*

open class MyApplication : Application() {

    lateinit var synchonizer : Synchronizer

    override fun onCreate() {
        super.onCreate()
        synchonizer = createSynchronizer()

        // Dagger 2
        // val component = DaggerMyComponent.create()
        // component.inject(this)
    }

    open fun createRandomizer(): Randomizer = RandomizerImpl(Random())
    open fun createSynchronizer(): Synchronizer = SynchronizerImpl(createRandomizer(), timer = Timer())
}