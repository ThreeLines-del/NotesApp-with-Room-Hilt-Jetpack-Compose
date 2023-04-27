package com.linesapp.notesappwithroomjetpackcompose.repository

import com.linesapp.notesappwithroomjetpackcompose.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(note: Note)
}