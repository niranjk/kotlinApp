package com.niranjankhatri.kotlinapp.ui

import androidx.test.espresso.idling.CountingIdlingResource
import com.niranjankhatri.kotlinapp.MyApplication
import com.niranjankhatri.kotlinapp.testing.ui.Randomizer
import com.niranjankhatri.kotlinapp.testing.ui.Synchronizer

class TestMyApplication: MyApplication() {

    val countingIdlingResource = CountingIdlingResource("Timer resource")

    override fun createRandomizer(): Randomizer {
        return TestRandomizer()
    }

    override fun createSynchronizer(): Synchronizer {
        return TestSynchronizer(super.createSynchronizer(), countingIdlingResource)
    }
}