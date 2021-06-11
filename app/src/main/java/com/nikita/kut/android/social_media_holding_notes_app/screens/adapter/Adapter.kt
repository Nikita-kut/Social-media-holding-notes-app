package com.nikita.kut.android.social_media_holding_notes_app.screens.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.social_media_holding_notes_app.R
import com.nikita.kut.android.social_media_holding_notes_app.databinding.NoteItemBinding
import com.nikita.kut.android.social_media_holding_notes_app.model.Note

class Adapter(private val listener: ToDoEvents) : RecyclerView.Adapter<Adapter.NoteViewHolder>() {

    private val differ = AsyncListDiffer(this, NoteDiffUtilCallBack())

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NoteViewHolder,
        position: Int
    ) {
        holder.bind(differ.currentList[position], listener)
    }

    override fun getItemCount(): Int = differ.currentList.size

    fun setAllNotes(newNotes: List<Note>) {
        differ.submitList(newNotes)
    }

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = NoteItemBinding.bind(view)

        fun bind(note: Note, listener: ToDoEvents) {
            binding.cardTitle.text = note.title
            binding.cardDescription.text = note.description
            binding.completed.isChecked = note.done
            binding.delete.setOnClickListener {
                listener.onItemDeleted(note, adapterPosition)
            }
            binding.root.setOnClickListener {
                listener.onItemClicked(note)
            }
        }
    }

    interface ToDoEvents {
        fun onItemDeleted(note: Note, position: Int)
        fun onItemClicked(note: Note)
    }
}