package com.niranjankhatri.kotlinapp.dogapp.storage.preference

import android.content.SharedPreferences

const val DEFAULT_NO_OF_RESULTS = 10
const val KEY_NR_RESULTS = "key_br_results"

class DownloadPreferenceWrapper(
    private val sharedPreferences: SharedPreferences
) {
    fun getNumberOfResults(): Int{
        return sharedPreferences.getInt(KEY_NR_RESULTS, DEFAULT_NO_OF_RESULTS)
    }

    fun saveNumberOfResults(nrResults: Int){
        sharedPreferences.edit().putInt(KEY_NR_RESULTS, nrResults).apply()
    }
}