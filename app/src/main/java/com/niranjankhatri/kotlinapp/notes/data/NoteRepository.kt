package com.niranjankhatri.kotlinapp.notes.data

import androidx.lifecycle.LiveData
import com.niranjankhatri.kotlinapp.notes.room.Note

interface NoteRepository {
    fun insertNote(note: Note)
    fun getAllNotes(): LiveData<List<Note>>
    fun getNoteCount(): LiveData<Int>
}