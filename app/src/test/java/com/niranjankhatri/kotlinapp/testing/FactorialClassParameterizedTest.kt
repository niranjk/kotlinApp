package com.niranjankhatri.kotlinapp.testing

import com.niranjankhatri.kotlinapp.rules.ResultRule
import com.niranjankhatri.kotlinapp.testing.junit.FactorialClass
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.math.BigInteger

@RunWith(Parameterized::class)
class FactorialClassParameterizedTest(
    private val input: Int,
    private val expected: BigInteger
) {
    @JvmField
    @Rule
    val resultRule = ResultRule()

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<Array<Any>> = listOf(
            arrayOf(0, BigInteger.ONE),
            arrayOf(1, BigInteger.ONE),
            arrayOf(2, BigInteger.valueOf(2)),
            arrayOf(3, BigInteger.valueOf(6)),
            arrayOf(4, BigInteger.valueOf(24)),
            arrayOf(5, BigInteger.valueOf(120))
        )
    }
    private val factorialClass = FactorialClass()
    @Test
    fun factorialParameterizedTest() {
        // Act : call the method under test
        val result = factorialClass.factorial(input)
        // Assert : we verify our results
        Assert.assertEquals(expected, result)
    }

}