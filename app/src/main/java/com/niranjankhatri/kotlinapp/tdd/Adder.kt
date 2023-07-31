package com.niranjankhatri.kotlinapp.tdd

// criteria
/**
 * 1. if n <= 0 return -1
 * 2. return correct values for Int.MAX_VALUE
 * 3. function should be quick : even for Int.MAX_VALUE
 *
 * sum function will return the sum of numbers from 1 to n.
 */
class Adder {
    fun sum(n: Int): Long {
        return if (n > 0) (n * (n.toLong() + 1) / 2)
        else -1
    }
}
