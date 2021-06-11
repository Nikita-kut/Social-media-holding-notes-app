package com.nikita.kut.android.social_media_holding_notes_app.screens.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nikita.kut.android.social_media_holding_notes_app.model.Note

class NoteDiffUtilCallBack : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean = oldItem == newItem
}