package com.nikita.kut.android.social_media_holding_notes_app.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "Title") var title: String?,
    @ColumnInfo(name = "Description") var description: String?,
    @ColumnInfo(name = "CreatedAt") var createdAt: Long,
    @ColumnInfo(name = "Done") var done: Boolean
) : Parcelable