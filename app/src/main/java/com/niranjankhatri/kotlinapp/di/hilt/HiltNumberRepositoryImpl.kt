package com.niranjankhatri.kotlinapp.di.hilt

import java.util.Random

class HiltNumberRepositoryImpl(
    private val random: Random
) : HiltNumberRepository{
    override fun generateNextNumber(): Int {
        return random.nextInt()
    }
}