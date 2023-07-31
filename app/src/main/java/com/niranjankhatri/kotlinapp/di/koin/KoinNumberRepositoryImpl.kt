package com.niranjankhatri.kotlinapp.di.koin

import java.util.Random

class KoinNumberRepositoryImpl(
    private val random: Random
) : KoinNumberRepository{
    override fun generateNextNumber(): Int {
        return random.nextInt()
    }
}