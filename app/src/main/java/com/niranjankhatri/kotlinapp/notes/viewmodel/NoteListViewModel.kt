package com.niranjankhatri.kotlinapp.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.niranjankhatri.kotlinapp.notes.data.NoteRepository
import com.niranjankhatri.kotlinapp.notes.room.Note

class NoteListViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    fun getNoteListLiveData(): LiveData<List<Note>> =
        noteRepository.getAllNotes()
}