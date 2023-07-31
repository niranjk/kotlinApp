package com.niranjankhatri.kotlinapp.testing.mockito

import com.niranjankhatri.kotlinapp.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class SpecificStringConcatenatorTest {

    @Mock
   lateinit var stringConcatenator: StringConcatenator
   @InjectMocks
   lateinit var specificStringConcatenator : SpecificStringConcatenator
    @Test
    fun concatenateSpecificStrings() {
        val expected = "expected"
       whenever(stringConcatenator.concatenate(R.string.string_1, R.string.string_2))
            .thenReturn(expected)
        val result = specificStringConcatenator.concatenateSpecificStrings()
        assertEquals(expected, result)
    }

    @Test
    fun concatenateWithCallbacksTest(){
        val expected = "expected"
        whenever(stringConcatenator.concatenate(R.string.string_1,
            R.string.string_2)).thenReturn(expected)
        val callback = mock<SpecificStringConcatenator.Callback>()
        specificStringConcatenator.concatenateWithCallback(callback)
        verify(callback).onStringReady(expected)
    }
}