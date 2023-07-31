package com.niranjankhatri.kotlinapp.dogapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.niranjankhatri.kotlinapp.dogapp.api.Dog
import com.niranjankhatri.kotlinapp.dogapp.api.DownloadService
import com.niranjankhatri.kotlinapp.dogapp.storage.filesystem.ProviderFileHandler
import com.niranjankhatri.kotlinapp.dogapp.storage.preference.DownloadPreferenceWrapper
import com.niranjankhatri.kotlinapp.dogapp.storage.room.DogDao
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class DownloadRepositoryImpl(
    private val downloadPreferenceWrapper: DownloadPreferenceWrapper,
    private val providerFileHandler: ProviderFileHandler,
    private val downloadService: DownloadService,
    private val dogDao: DogDao,
    private val dogMapper: DogMapper,
    private val executor: Executor
) : DownloadRepository{

    override fun loadDogList(): LiveData<Result<List<DogUi>>> {
        val result = MediatorLiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<List<DogUi>>>()
        result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Loading())
        result.addSource(dogDao.loadDogs()){dogEntities ->
            result.postValue(
                com.niranjankhatri.kotlinapp.dogapp.repository.Result.Success(
                    dogEntities.map {
                        dogMapper.mapEntityToUi(it)
                    }
                )
            )
        }
        downloadService.getDogs(downloadPreferenceWrapper.getNumberOfResults())
            .enqueue(object : Callback<Dog> {
                override fun onResponse(call: Call<Dog>, response: Response<Dog>) {
                    if (response.isSuccessful){
                        executor.execute {
                            response.body()?.let {
                                dogDao.deleteAll()
                                dogDao.insertDogs(
                                    dogMapper.mapServiceToEntity(it)
                                )
                            }
                        }
                    } else {
                        result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Error())
                    }
                }

                override fun onFailure(call: Call<Dog>, t: Throwable) {
                    result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Error())
                }

            })
        return  result
    }

    override fun downloadFile(url: String): LiveData<Result<Unit>> {
        val result = MutableLiveData<com.niranjankhatri.kotlinapp.dogapp.repository.Result<Unit>>()

        result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Loading())
        downloadService.downloadFile(url)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful){
                        executor.execute {
                            try {
                                response.body()?.let {
                                    val name = url.substring(url.lastIndexOf("/") + 1)
                                    providerFileHandler.writeStream(
                                        name,
                                        response.body()!!.byteStream()
                                    )
                                    result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Success(Unit))
                                }
                            } catch (e: java.lang.Exception){
                                e.printStackTrace()
                                result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Error())
                            }
                        }
                    } else {
                        result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Error())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    result.postValue(com.niranjankhatri.kotlinapp.dogapp.repository.Result.Error())
                    t.printStackTrace()
                }

            })
        return result
    }


}