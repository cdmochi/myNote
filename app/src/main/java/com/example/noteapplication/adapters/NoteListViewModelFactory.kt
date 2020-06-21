package com.example.noteapplication.adapters

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.noteapplication.Data.NoteDao
import com.example.noteapplication.Data.NoteRepository
import java.lang.IllegalArgumentException

class NoteListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            return NoteListViewModel(application) as T
        }
        throw IllegalArgumentException("Class not fount")
    }


}