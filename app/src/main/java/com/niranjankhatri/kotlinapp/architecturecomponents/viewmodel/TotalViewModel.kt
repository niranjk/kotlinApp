package com.niranjankhatri.kotlinapp.architecturecomponents.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Data Stream Mechanism
 *
 * 1. Live Data
 * 2. Coroutine and Flow
 */
class TotalViewModel : ViewModel(){

    // Live Data Approach
    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total

    // Coroutine and flows
    private val _totalFlow = MutableStateFlow(0)
    val totalFlow : StateFlow<Int> = _totalFlow

    init {
        _total.postValue(0)
    }

    fun increaseTotal() {
        _total.postValue((_total.value ?: 0) + 1)
    }

    fun increaseTotalUsingFlow(){
        _totalFlow.value = _totalFlow.value + 1
    }
}