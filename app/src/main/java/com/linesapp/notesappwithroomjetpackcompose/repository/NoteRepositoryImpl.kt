package com.linesapp.notesappwithroomjetpackcompose.repository

import com.linesapp.notesappwithroomjetpackcompose.database.NoteDao
import com.linesapp.notesappwithroomjetpackcompose.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
): NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun addNote(note: Note) {
        return dao.addNote(note)
    }

    override suspend fun updateNote(note: Note) {
        return dao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }

}