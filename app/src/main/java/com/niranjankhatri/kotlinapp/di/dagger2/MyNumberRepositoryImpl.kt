package com.niranjankhatri.kotlinapp.di.dagger2

import java.util.*

class MyNumberRepositoryImpl(
    private val random: Random
) : MyNumberRespository{
    override fun generateNextNumber(): Int {
        return random.nextInt()
    }
}