package com.example.notekeeper.note_list

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeper.EXTRA_NOTE_POSITION
import com.example.notekeeper.NoteInfo
import com.example.notekeeper.R
import com.example.notekeeper.R.string.drawer_close
import com.example.notekeeper.R.string.drawer_open
import com.example.notekeeper.note_edit.MainActivity
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    private val presenter = NoteListPresenter(this)
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        setOnClickListener()
        setOnItemClickListener()
        presenter.populateNoteList()
        toggle = ActionBarDrawerToggle(this, drawer, drawer_open, drawer_close)
        drawer.addDrawerListener(toggle)

        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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
