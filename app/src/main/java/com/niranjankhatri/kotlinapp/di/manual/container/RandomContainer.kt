package com.niranjankhatri.kotlinapp.di.manual.container

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.niranjankhatri.kotlinapp.di.manual.ManualViewModel
import com.niranjankhatri.kotlinapp.di.manual.NumberRepository

class RandomContainer(
    private val numberRepository: NumberRepository
) {
    fun getManualViewModelFactory(): ViewModelProvider.Factory{
        return object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ManualViewModel(numberRepository) as T
            }
        }
    }
}