package com.niranjankhatri.kotlinapp.di.dagger2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
class MainModule {
    @Provides
    fun provideMyNumberViewModelFactory(
        numberRespository: MyNumberRespository
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MyNumberViewModel(numberRespository) as T
            }
        }
    }
}