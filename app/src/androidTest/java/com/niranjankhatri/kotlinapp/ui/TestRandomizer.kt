package com.niranjankhatri.kotlinapp.ui

import com.niranjankhatri.kotlinapp.testing.ui.Randomizer

class TestRandomizer : Randomizer{
    override fun getTimeToWait(): Int {
        return 1
    }
}