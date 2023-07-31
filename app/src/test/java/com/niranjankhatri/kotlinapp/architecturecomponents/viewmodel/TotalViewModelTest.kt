package com.niranjankhatri.kotlinapp.architecturecomponents.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test

class TotalViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var totalViewModel: TotalViewModel

    @Before
    fun setUp(){
        totalViewModel = TotalViewModel()
    }

    @Test
    fun increaseTotal() {
        val total = 5
        // like a simulation that you are pressing your button
        for (i in 0 until total){
            totalViewModel.increaseTotal()
        }
        assertEquals(5, totalViewModel.total.value)
    }
}