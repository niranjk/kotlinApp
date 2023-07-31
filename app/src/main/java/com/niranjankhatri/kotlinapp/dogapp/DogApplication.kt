package com.niranjankhatri.kotlinapp.dogapp

import android.app.Application
import androidx.room.Room
import com.niranjankhatri.kotlinapp.dogapp.api.DownloadService
import com.niranjankhatri.kotlinapp.dogapp.repository.DogMapper
import com.niranjankhatri.kotlinapp.dogapp.repository.DownloadRepository
import com.niranjankhatri.kotlinapp.dogapp.repository.DownloadRepositoryImpl
import com.niranjankhatri.kotlinapp.dogapp.storage.filesystem.FileToUriMapper
import com.niranjankhatri.kotlinapp.dogapp.storage.filesystem.ProviderFileHandler
import com.niranjankhatri.kotlinapp.dogapp.storage.preference.DownloadPreferenceWrapper
import com.niranjankhatri.kotlinapp.dogapp.storage.room.DogDatabase
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.Executors

class DogApplication : Application() {

    lateinit var downloadRepository : DownloadRepository
    lateinit var preferenceWrapper: DownloadPreferenceWrapper

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val downloadService = retrofit.create(DownloadService::class.java)
        val database = Room.databaseBuilder(
            applicationContext,
            DogDatabase::class.java,
            "dog-db"
        ).build()
        preferenceWrapper = DownloadPreferenceWrapper(
            getSharedPreferences("my_dog_shared_preferences", MODE_PRIVATE)
        )
        downloadRepository = DownloadRepositoryImpl(
            preferenceWrapper,
            ProviderFileHandler(
                this,
                FileToUriMapper()
            ),
            downloadService,
            database.dogDao(),
            DogMapper(),
            Executors.newSingleThreadExecutor()
        )

    }
}