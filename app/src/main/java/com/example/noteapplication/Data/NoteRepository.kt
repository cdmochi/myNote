package com.example.noteapplication.Data

import android.app.Application
import androidx.lifecycle.LiveData

class NoteRepository(application : Application) {

    private var noteDao : NoteDao
    private var notes : LiveData<List<Note>>

    init {
        val db = NoteDatabase.getInstance(application)
        noteDao = db.noteDao
        notes = noteDao.getAllNote()
    }

    suspend fun insertNote(note : Note) {
        noteDao.insert(note)
    }
    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    fun getAllNote(): LiveData<List<Note>> {
        return notes
    }

    suspend fun updateCheckedNote(id: Long, isCompleted: Boolean) {
        noteDao.updateNoteById(id,isCompleted)
    }



}