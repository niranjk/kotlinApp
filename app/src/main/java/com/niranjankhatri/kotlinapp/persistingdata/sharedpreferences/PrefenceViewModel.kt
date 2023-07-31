package com.niranjankhatri.kotlinapp.persistingdata.sharedpreferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PrefenceViewModel(
    private val preferenceWrapper: PreferenceWrapper
): ViewModel() {
    fun saveText(text: String){
        preferenceWrapper.saveText(text)
    }
    fun getText(): LiveData<String>{
        return preferenceWrapper.getText()
    }
}