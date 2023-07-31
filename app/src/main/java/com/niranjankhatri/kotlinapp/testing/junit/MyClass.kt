package com.niranjankhatri.kotlinapp.testing.junit

class MyClass {
    companion object{
        var result = 1
    }
    @Throws(NegativeNumberException::class)
    fun factorial(n: Int): Int{
        if (n < 0){
            throw NegativeNumberException
        }
        for (i in 1..n){
            result = result.times(i)
        }
        return result
    }
    object NegativeNumberException: Throwable()
}