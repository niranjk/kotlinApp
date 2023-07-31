package com.niranjankhatri.kotlinapp.di.dagger2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyNumberViewModel(
    private val numberRespository: MyNumberRespository
) : ViewModel(){

    private val _numberLiveData = MutableLiveData<Int>()
    val numberLiveData: LiveData<Int> =  _numberLiveData

    fun generateNextNumber(){
        _numberLiveData.postValue(numberRespository.generateNextNumber())
    }
}