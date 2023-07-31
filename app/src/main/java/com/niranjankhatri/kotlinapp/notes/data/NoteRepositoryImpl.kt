package com.niranjankhatri.kotlinapp.notes.data

import androidx.lifecycle.LiveData
import com.niranjankhatri.kotlinapp.notes.room.Note
import com.niranjankhatri.kotlinapp.notes.room.NoteDao
import java.util.concurrent.Executor

class NoteRepositoryImpl(
    private val executor: Executor,
    private val noteDao: NoteDao
) : NoteRepository {

    override fun insertNote(note: Note) {
        executor.execute {
            noteDao.insertNote(note)
        }
    }

    override fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.loadNotes()
    }

    override fun getNoteCount(): LiveData<Int> {
        return noteDao.loadNoteCount()
    }
}