package com.nikita.kut.android.social_media_holding_notes_app.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.nikita.kut.android.social_media_holding_notes_app.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

class NoteRepository(app: Application) {

    private val TAG = "NoteRepository"
    private val noteDao: NoteDao
    private val activeNotes: LiveData<List<Note>>


    init {
        val dataBase = DataBase.getInstance(app.applicationContext)
        noteDao = dataBase?.noteDao() ?: throw RuntimeException("task repository")
        activeNotes = noteDao.getAllNote()
    }

    fun saveNote(note: Note) = runBlocking {
        Log.d(TAG, ":saveNote()")
        this.launch(Dispatchers.IO) { noteDao.saveNote(note) }
    }

    fun updateNote(note: Note) = runBlocking {
        Log.d(TAG, ":updateNote()")
        this.launch(Dispatchers.IO) { noteDao.updateNote(note) }
    }

    fun deleteNote(note: Note) = runBlocking {
        Log.d(TAG, ":deleteNote()")
        this.launch(Dispatchers.IO) { noteDao.deleteNote(note) }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        Log.d(TAG, ":getAllNotes()")
        return activeNotes
    }
}