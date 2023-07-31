package com.niranjankhatri.kotlinapp.testing

import com.niranjankhatri.kotlinapp.testing.junit.FactorialClass
import org.junit.Assert
import org.junit.Test
import java.math.BigInteger

class FactorialClassLargeNumberTest {

    private val factorialClass = FactorialClass()
    @Test
    fun factorial() {
        // Arrange : initalize the inputs
        val n = 20
        // Act : call the method under test
        val result = factorialClass.factorial(n)
        // Assert : we verify our results
        /// 2076180480, -2102132736
        // 15511210043330985984000000
        Assert.assertEquals( BigInteger("2432902008176640000"), result)
    }
}