package com.niranjankhatri.kotlinapp.di.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Random

@Module
@InstallIn(SingletonComponent::class)
class HiltApplicationModule {

    @Provides
    fun provideRandom(): Random = Random()

    @Provides
    fun provideHiltNumberRepository(random: Random) : HiltNumberRepository =
        HiltNumberRepositoryImpl(random)
}