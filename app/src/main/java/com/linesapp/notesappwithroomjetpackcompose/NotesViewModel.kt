package com.linesapp.notesappwithroomjetpackcompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linesapp.notesappwithroomjetpackcompose.model.Note
import com.linesapp.notesappwithroomjetpackcompose.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {
    val getAllNotes: Flow<List<Note>> = noteRepository.getAllNotes()

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }
}