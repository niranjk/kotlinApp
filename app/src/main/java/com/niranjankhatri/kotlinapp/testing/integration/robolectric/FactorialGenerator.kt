package com.niranjankhatri.kotlinapp.testing.integration.robolectric

import java.math.BigInteger

class FactorialGenerator {
    @Throws(FactNotFound::class)
    fun factorial(n: Int): BigInteger{
        if(n < 0){
             throw FactNotFound
        }
        var result = BigInteger.ONE
        for (i in 1..n){
            result = result.times(i.toBigInteger())
        }
        return result
    }

    object FactNotFound : Throwable()
}