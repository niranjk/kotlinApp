package com.niranjankhatri.kotlinapp.di.dagger2

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import java.util.Random

@DisableInstallInCheck  // add this in case of Hilt
@Module
class ApplicationModule {
    @Provides
    fun provideRandom(): Random = Random()
    @Provides
    fun provideMyNumberRepository(random: Random): MyNumberRespository = MyNumberRepositoryImpl(random)
}