package com.niranjankhatri.kotlinapp.di.hilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.EventObject
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class HiltViewModel @Inject constructor( private val numberRepository: HiltNumberRepository)
    : ViewModel() {

    private val _numberLiveData = MutableLiveData<Int>()
    val numberLiveData : LiveData<Int> = _numberLiveData

    fun generateNextNumber(){
        _numberLiveData.postValue(numberRepository.generateNextNumber())
    }
}