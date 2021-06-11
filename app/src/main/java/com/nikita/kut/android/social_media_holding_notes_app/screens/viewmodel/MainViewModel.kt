package com.nikita.kut.android.social_media_holding_notes_app.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nikita.kut.android.social_media_holding_notes_app.data.NoteRepository
import com.nikita.kut.android.social_media_holding_notes_app.model.Note

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = NoteRepository(app)

    fun saveNote(note: Note) {
        repository.saveNote(note)
    }

    fun updateNote(note: Note) {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return repository.getAllNotes()
    }
}