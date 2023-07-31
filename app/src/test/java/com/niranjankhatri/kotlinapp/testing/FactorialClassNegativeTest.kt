package com.niranjankhatri.kotlinapp.testing

import com.niranjankhatri.kotlinapp.testing.junit.FactorialClass
import org.junit.Assert
import org.junit.Test

class FactorialClassNegativeTest {

    private val factorialClass = FactorialClass()

    @Test(expected = FactorialClass.FactorialNotFoundException::class)
    fun factorial() {
        // Arrange : initalize the inputs
        val n = -10
        // Act : call the method under test
        val result = factorialClass.factorial(n)
        // Assert : we verify our results
        Assert.assertEquals(6, result)
    }
}