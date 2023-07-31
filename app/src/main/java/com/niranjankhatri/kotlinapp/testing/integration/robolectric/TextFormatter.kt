package com.niranjankhatri.kotlinapp.testing.integration.robolectric

import android.content.Context
import com.niranjankhatri.kotlinapp.R

class TextFormatter(
    private val factorialGenerator: FactorialGenerator,
    private val context: Context
) {
    fun getFactorialResult(n: Int): String{
        return try {
            context.getString(R.string.result,
            factorialGenerator.factorial(n).toString())
        } catch (e: FactorialGenerator.FactNotFound){
            context.getString(R.string.error)
        }
    }
}