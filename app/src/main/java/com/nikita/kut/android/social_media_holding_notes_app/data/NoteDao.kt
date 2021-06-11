package com.nikita.kut.android.social_media_holding_notes_app.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nikita.kut.android.social_media_holding_notes_app.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY id ASC")
    fun getAllNote(): LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
    fun getNote(id: Int): Note

    @Insert
    suspend fun saveNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}