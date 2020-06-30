package com.example.noteapplication.adapters

import android.app.Application
import android.view.View
import android.widget.CompoundButton
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
            Timber.d("inserting Note in CurrentThread:${Thread.currentThread().name}")
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteRepository.deleteNote(note)
            Timber.d("Delete Note in CurrentThread:${Thread.currentThread().name}")
        }

    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteRepository.updateNote(note)
            Timber.d("Updating Note in CurrentThread:${Thread.currentThread().name}")
        }
    }

    fun getAllNotes() : LiveData<List<Note>>{
       return noteRepository.getAllNote()
    }

    fun onClickCheckBox(view : View, note : Note) {
        Timber.d("note is Clicked ${note.title}")
    }



    fun onCheckStatusChange(noteId : Long, isChecked : Boolean) {
        viewModelScope.launch {
            checkedNoteInBg(noteId,isChecked)
        }
    }

    private suspend fun checkedNoteInBg(noteId: Long, isChecked: Boolean) {
        withContext(IO){
            noteRepository.updateCheckedNote(noteId,isChecked)
        }
    }





}