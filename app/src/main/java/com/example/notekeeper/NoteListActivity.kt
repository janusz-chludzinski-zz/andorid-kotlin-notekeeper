package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        setOnClickListener()

        populateNoteList()

        setOnItemClickListener()

    }

    override fun onResume() {
        super.onResume()
        populateNoteList()
    }

    private fun setOnClickListener() {
        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, MainActivity::class.java)
            startActivity(activityIntent)
        }
    }

    private fun setOnItemClickListener() {
        listNotes.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_NOTE_POSITION, position)
            startActivity(intent)
        }
    }

    private fun populateNoteList() {
        listNotes.adapter = ArrayAdapter<NoteInfo>(
            this,
            android.R.layout.simple_list_item_1,
            DataManager.notes
        )
    }
}
