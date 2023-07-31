package com.niranjankhatri.kotlinapp.di.manual

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ManualViewModel(
    private val numberRepository: NumberRepository
) : ViewModel() {

    private val _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> = _numberLiveData

    fun generateNextNumber() {
        _numberLiveData.postValue(
            numberRepository.generateNextNumber()
        )
    }
}