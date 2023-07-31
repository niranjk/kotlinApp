package com.niranjankhatri.kotlinapp.ui

import androidx.test.espresso.idling.CountingIdlingResource
import com.niranjankhatri.kotlinapp.testing.ui.Synchronizer

class TestSynchronizer(
    private val synchronizer: Synchronizer,
    private val countingIdlingResource: CountingIdlingResource
) : Synchronizer {

    override fun executeAfterDelay(callback: (Int) -> Unit) {
        countingIdlingResource.increment() // when you want espresso to wait for your code complete its execution
        synchronizer.executeAfterDelay{
            callback(it)
            countingIdlingResource.decrement() // when you want espresso to verify your code.
        }
    }
}