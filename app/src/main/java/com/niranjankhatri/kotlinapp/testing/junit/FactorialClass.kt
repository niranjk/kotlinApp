package com.niranjankhatri.kotlinapp.testing.junit

import java.math.BigInteger

class FactorialClass {

    companion object {
        var result = BigInteger.ONE
    }

    @Throws(FactorialNotFoundException::class)
    fun factorial(n: Int): BigInteger{
        if (n < 0) {
            throw FactorialNotFoundException
        }

        for (i in 1..n){
            result = result.times(i.toBigInteger())
        }
        return result
    }
    object FactorialNotFoundException : Throwable()
}