package com.niranjankhatri.kotlinapp.testing.mockito

import android.content.Context
import org.junit.Assert.*

import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class StringConcatenatorTest {
   // Android Framework Classes
    private val context = Mockito.mock(Context::class.java)
    private val stringConcatenator = StringConcatenator(context)
    @Test
    fun concatenate() {
        val stringRes1 = 1
        val stringRes2 = 2
        val string1 = "string1"
        val string2 = "string2"
        whenever(context.getString(stringRes1)).thenReturn(string1)
        whenever(context.getString(stringRes2)).thenReturn(string2)
        val result = stringConcatenator.concatenate(stringRes1, stringRes2)
        assertEquals(
            string1.plus(string2),
            result
        )
    }
}