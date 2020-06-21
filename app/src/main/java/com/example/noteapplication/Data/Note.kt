package com.example.noteapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note (

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,

    var title: String = "",

    var description: String = "",

    var isChecked: Boolean = false)





