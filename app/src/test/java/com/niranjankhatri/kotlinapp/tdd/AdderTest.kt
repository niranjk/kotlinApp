package com.niranjankhatri.kotlinapp.tdd

import org.junit.Assert
import org.junit.Test

class AdderTest {

    private val adder = Adder()

    @Test
    fun sumSuccessTest() {
        Assert.assertEquals(1, adder.sum(1))
        Assert.assertEquals(3, adder.sum(2))
        Assert.assertEquals(55, adder.sum(10))
        Assert.assertEquals(210, adder.sum(20))
        Assert.assertEquals(2305843008139952128L, adder.sum(Int.MAX_VALUE))
    }

    @Test
    fun sumFailureTest() {
        Assert.assertEquals(-1, adder.sum(0))
        Assert.assertEquals(-1, adder.sum(-1))
        Assert.assertEquals(-1, adder.sum(-2))
    }
}