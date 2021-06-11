package com.nikita.kut.android.social_media_holding_notes_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikita.kut.android.social_media_holding_notes_app.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        var instance: DataBase? = null
            private set
        const val DATABASENAME = "note_data_base"

        fun getInstance(context: Context): DataBase? {
            if (instance == null) {
                synchronized(DataBase::class.java) {
                    instance =
                        Room.databaseBuilder(context, DataBase::class.java, DATABASENAME).build()
                }
            }
            return instance
        }
    }
}