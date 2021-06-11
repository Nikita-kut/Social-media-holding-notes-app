package com.nikita.kut.android.social_media_holding_notes_app.screens

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.nikita.kut.android.social_media_holding_notes_app.R
import com.nikita.kut.android.social_media_holding_notes_app.const.Const.INTENT_NOTE
import com.nikita.kut.android.social_media_holding_notes_app.databinding.ActivityCreateNoteBinding
import com.nikita.kut.android.social_media_holding_notes_app.model.Note

class CreateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateNoteBinding
    private var note: Note? = null
    private val etTitle: EditText by lazy {findViewById(R.id.edit_text_task_title)}
    private val etDescription: EditText by lazy {findViewById(R.id.edit_text_task_description)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()

        if (intent != null && intent.hasExtra(INTENT_NOTE)) {
            val note = intent.getParcelableExtra<Note>(INTENT_NOTE)
            this.note = note
            etTitle.setText(note?.title)
            etTitle.setSelection(etTitle.text.length)
            etDescription.setText(note?.description)
        }
        binding.toolbarActivityCreateTask.title = if (note != null) "Edit note" else "Create note"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> saveToDo()
            android.R.id.home -> finish()
        }
        return true
    }

    private fun saveToDo() {
        if (validateForms()) {
            val id = if (note != null) note?.id else null
            val todo = Note(
                id = id,
                title = etTitle.text.toString(),
                description = etDescription.text.toString(),
                createdAt = System.currentTimeMillis(),
                done = false
            )

            val intent = Intent()
            intent.putExtra(INTENT_NOTE, todo)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun validateForms(): Boolean {
        val tvInputTitle: TextInputLayout = findViewById(R.id.input_task_title)
        val tvInputDescription: TextInputLayout = findViewById(R.id.input_task_description)
        if (etTitle.text.isEmpty()) {
            tvInputTitle.error = "Enter title"
            etTitle.requestFocus()
            return false
        }
        if (etDescription.text.isEmpty()) {
            tvInputDescription.error = "Enter description"
            etDescription.requestFocus()
            return false
        }
        return true
    }

    private fun setToolbar() {
        with(binding.toolbarActivityCreateTask) {
            setTitle(R.string.notes_activity_title)
            setSupportActionBar(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }


}