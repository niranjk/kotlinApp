package com.niranjankhatri.kotlinapp.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.niranjankhatri.kotlinapp.notes.NotesApplication
import com.niranjankhatri.kotlinapp.notes.data.NoteRepository
import com.niranjankhatri.kotlinapp.notes.room.Note

class CountNotesViewModel(application: Application): AndroidViewModel(application) {
    private val noteRepository: NoteRepository = (application as NotesApplication).notesRepository

    fun insertNote(text: String){
        noteRepository.insertNote(Note(id = 0, text))
    }

    fun getNoteCountLiveData(): LiveData<Int> =
        noteRepository.getNoteCount()
}