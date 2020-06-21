package com.example.noteapplication

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapplication.Data.Note
import com.example.noteapplication.Data.NoteRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AddNoteViewModel(application : Application) : AndroidViewModel(application) {

    private val noteRepository : NoteRepository = NoteRepository(application)
    private val _currentNote = MutableLiveData<Note>()
    val currentNote : LiveData<Note>
        get() = _currentNote


    init {
        _currentNote.value = Note()
    }


    fun setNote(title: String, des: String) {
        _currentNote.value?.title = title
        _currentNote.value?.description = des
    }

    fun saveNote() {
        viewModelScope.launch {
            saveNoteToDb()
        }
    }

    private suspend fun saveNoteToDb() {
        withContext(IO) {
            Timber.d("Saving to Database... SuspenseFunction is called")
            noteRepository.insertNote(_currentNote.value!!)
        }
    }

}