package com.niranjankhatri.kotlinapp.dogapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.niranjankhatri.kotlinapp.dogapp.repository.DogUi
import com.niranjankhatri.kotlinapp.dogapp.repository.DownloadRepository

class DogViewModel(
    private val downloadRepository: DownloadRepository
) : ViewModel(){
    private val _dogsLiveData : MediatorLiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<List<DogUi>>> by lazy {
        MediatorLiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<List<DogUi>>>()
    }
    val dogsLiveData : LiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<List<DogUi>>> = _dogsLiveData

    private val _downloadResult : MediatorLiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<Unit>> by lazy {
        MediatorLiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<Unit>>()
    }
    val downloadResult : LiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<Unit>> = _downloadResult

    fun getDogs(){
        _dogsLiveData.addSource(downloadRepository.loadDogList()){
            _dogsLiveData.postValue(it)
        }
    }

    fun downloadFile(url: String){
        _downloadResult.addSource(downloadRepository.downloadFile(url)){
            _downloadResult.postValue(it)
        }
    }
}