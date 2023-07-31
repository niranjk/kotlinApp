package com.niranjankhatri.kotlinapp.dogapp.repository

import androidx.lifecycle.LiveData

interface DownloadRepository {

    fun loadDogList(): LiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<List<DogUi>>>
    fun downloadFile(url: String): LiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<Unit>>
}