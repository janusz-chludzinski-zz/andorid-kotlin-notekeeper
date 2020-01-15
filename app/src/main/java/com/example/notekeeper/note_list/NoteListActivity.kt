package com.example.notekeeper.note_list

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeper.*
import com.example.notekeeper.note_edit.MainActivity

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    private val presenter = NoteListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        setOnClickListener()
        setOnItemClickListener()

        presenter.populateNoteList()

    }

    override fun onResume() {
        super.onResume()
        presenter.populateNoteList()
    }

    fun populateNoteList(notes: List<NoteInfo>) {
        listNotes.adapter = ArrayAdapter<NoteInfo>(
            this,
            android.R.layout.simple_list_item_1,
            notes
        )
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
}
