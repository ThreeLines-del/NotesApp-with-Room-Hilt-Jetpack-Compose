package com.linesapp.notesappwithroomjetpackcompose.di

import android.app.Application
import com.linesapp.notesappwithroomjetpackcompose.database.NoteDao
import com.linesapp.notesappwithroomjetpackcompose.database.NoteDatabase
import com.linesapp.notesappwithroomjetpackcompose.repository.NoteRepository
import com.linesapp.notesappwithroomjetpackcompose.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModules {

    @Provides
    @Singleton
    fun providesRepository(noteDao: NoteDao): NoteRepository{
        return NoteRepositoryImpl(noteDao)
    }

    @Provides
    @Singleton
    fun providesDatabase(application: Application): NoteDatabase{
        return NoteDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun providesDao(noteDatabase: NoteDatabase): NoteDao{
        return noteDatabase.noteDao()
    }
}