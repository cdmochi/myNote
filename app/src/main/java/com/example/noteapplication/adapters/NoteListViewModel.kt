package com.example.noteapplication.adapters

import android.app.Application
import androidx.lifecycle.*
import com.example.noteapplication.Data.Note
import com.example.noteapplication.Data.NoteDao
import com.example.noteapplication.Data.NoteRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import timber.log.Timber

class NoteListViewModel(application: Application) : AndroidViewModel(application){

    private val noteRepository : NoteRepository = NoteRepository(application)
    private val notes : LiveData<List<Note>>

    private val _isNoteClicked = MutableLiveData<Boolean>()
    val isNoteClicked : LiveData<Boolean>
        get() = _isNoteClicked

    init {
        notes = noteRepository.getAllNote()
    }

    fun insertNote(note : Note) {
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }

    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun getAllNotes() : LiveData<List<Note>>{
       return noteRepository.getAllNote()
    }



}