package com.niranjankhatri.kotlinapp.testing

import com.niranjankhatri.kotlinapp.rules.MyResultRule
import com.niranjankhatri.kotlinapp.testing.junit.MyClass
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MyClassTest(
    private val input: Int,
    private val expected: Int,
){
    @JvmField
    @Rule
    val myRule = MyResultRule()

    companion object {
        @Parameterized.Parameters
        @JvmStatic
        fun getData(): Collection<Array<Any>> = listOf(
            arrayOf(0, 1),
            arrayOf(1, 1),
            arrayOf(2, 2),
            arrayOf(3, 6),
            arrayOf(4, 24),
            arrayOf(5, 120)
        )
    }

    private val myClass = MyClass()

    @Test
    fun factorial_NegativeNumber_Test(){
        // Act
        val result = myClass.factorial(input)
        Assert.assertEquals(expected, result)
    }


}