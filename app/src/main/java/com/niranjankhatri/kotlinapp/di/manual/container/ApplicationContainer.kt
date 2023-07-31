package com.niranjankhatri.kotlinapp.di.manual.container

import com.niranjankhatri.kotlinapp.di.manual.NumberRepositoryImpl
import java.util.*

class ApplicationContainer {
    val numberRepository = NumberRepositoryImpl(Random())
}