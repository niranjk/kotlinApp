package com.niranjankhatri.kotlinapp.notes

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.niranjankhatri.kotlinapp.notes.data.NoteRepository
import com.niranjankhatri.kotlinapp.notes.data.NoteRepositoryImpl
import com.niranjankhatri.kotlinapp.notes.room.NotesDatabase
import com.niranjankhatri.kotlinapp.persistingdata.datastore.SettingsStore
import com.niranjankhatri.kotlinapp.persistingdata.sharedpreferences.PreferenceWrapper
import java.util.concurrent.Executors

class NotesApplication : Application(){

    lateinit var notesDatabase: NotesDatabase
    lateinit var notesRepository: NoteRepository

    lateinit var preferenceWrapper: PreferenceWrapper

    lateinit var settingsStore: SettingsStore

    override fun onCreate() {
        super.onCreate()
        notesDatabase = Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java,
            "shopping-notes-db"
        ).build()
        notesRepository = NoteRepositoryImpl(
            Executors.newSingleThreadExecutor(),
            notesDatabase.noteDao()
        )

        // Shared Preferences
        preferenceWrapper = PreferenceWrapper(getSharedPreferences("prefs", Context.MODE_PRIVATE))

        // Datastore
        settingsStore = SettingsStore(this)
    }
}