package com.example.noteapplication.Data

import android.app.Application
import android.content.Context
import androidx.core.content.contentValuesOf
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract val noteDao : NoteDao

    companion object {

        @Volatile
        var INSTANCE : NoteDatabase? = null

        fun getInstance(context : Context) : NoteDatabase{

                synchronized(this) {
                    var instance = INSTANCE
                    if (instance == null) {
                        instance = databaseBuilder(context.applicationContext,
                            NoteDatabase::class.java,"note_database")

                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE= instance
                    }
                    return instance

                }

        }

    }




}