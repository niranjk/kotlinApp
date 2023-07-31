package com.niranjankhatri.kotlinapp.testing

import com.niranjankhatri.kotlinapp.testing.junit.FactorialClass
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

class FactorialClassTest {

    private val factorialClass = FactorialClass()
    @Test
    fun factorial() {
        // Arrange : initalize the inputs
        val n = 3
        // Act : call the method under test
        val result = factorialClass.factorial(n)
        // Assert : we verify our results
        Assert.assertEquals(BigInteger.valueOf(6), result)
    }
}