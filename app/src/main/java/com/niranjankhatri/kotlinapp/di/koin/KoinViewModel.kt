package com.niranjankhatri.kotlinapp.di.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KoinViewModel(
    private val koinNumberRepository: KoinNumberRepository
) : ViewModel() {

    private val _numberLiveData = MutableLiveData<Int>()
    val numberLiveData : LiveData<Int> = _numberLiveData

    fun generateNextNumber(){
        _numberLiveData.postValue(koinNumberRepository.generateNextNumber())
    }
}