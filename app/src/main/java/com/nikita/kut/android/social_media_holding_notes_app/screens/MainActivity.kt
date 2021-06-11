package com.nikita.kut.android.social_media_holding_notes_app.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikita.kut.android.social_media_holding_notes_app.R
import com.nikita.kut.android.social_media_holding_notes_app.const.Const.EXTRA_MODE
import com.nikita.kut.android.social_media_holding_notes_app.const.Const.EXTRA_TASK
import com.nikita.kut.android.social_media_holding_notes_app.const.Const.INTENT_NOTE
import com.nikita.kut.android.social_media_holding_notes_app.databinding.ActivityMainBinding
import com.nikita.kut.android.social_media_holding_notes_app.model.Note
import com.nikita.kut.android.social_media_holding_notes_app.screens.adapter.Adapter
import com.nikita.kut.android.social_media_holding_notes_app.screens.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), Adapter.ToDoEvents {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val adapter: Adapter
        get() = binding.recyclerView.adapter as Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setRecyclerView()
        setViewModel()

        binding.fab.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivityForResult(intent, EXTRA_TASK)
        }
    }

    private fun setToolbar() {
        with(binding.toolbarActivityMain) {
            setTitle(R.string.app_name)
            setSupportActionBar(this)
        }
    }

    private fun setViewModel() {
        mainViewModel =
            ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        mainViewModel.getAllNotes().observe(this, Observer { adapter.setAllNotes(it) })

    }

    private fun setRecyclerView() {
        with(binding.recyclerView) {
            adapter = Adapter(this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        }
    }

    override fun onItemDeleted(note: Note, position: Int) {
        mainViewModel.deleteNote(note)
    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(this, CreateNoteActivity::class.java)
        intent.putExtra(INTENT_NOTE, note)
        startActivityForResult(intent, EXTRA_MODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val todo = data?.getParcelableExtra<Note>(INTENT_NOTE)!!
            when (requestCode) {
                EXTRA_TASK -> {
                    mainViewModel.saveNote(todo)
                }
               EXTRA_MODE -> {
                    mainViewModel.updateNote(todo)
                }
            }
        }
    }
}