package com.linesapp.notesappwithroomjetpackcompose.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.linesapp.notesappwithroomjetpackcompose.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase? = null
        fun getInstance(context: Context):NoteDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }

}