package com.example.noteapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    //CRUD OPERATION

    @Insert
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note : Note)

    @Update
    suspend fun update(note : Note)

    @Query("SELECT * FROM note_table")
    fun getAllNote() : LiveData<List<Note>>

}