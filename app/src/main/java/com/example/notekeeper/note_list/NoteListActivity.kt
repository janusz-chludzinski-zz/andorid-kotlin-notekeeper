package com.example.notekeeper.note_list

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeeper.R
import com.example.notekeeper.R.string.drawer_close
import com.example.notekeeper.R.string.drawer_open
import com.example.notekeeper.note_edit.NoteActivity
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*
import kotlinx.android.synthetic.main.drawer_header.*
import kotlinx.android.synthetic.main.drawer_header.view.*


class NoteListActivity : AppCompatActivity() {

    private val presenter = NoteListPresenter(this)
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        setOnClickListener()
        toggle = ActionBarDrawerToggle(this, drawer, drawer_open, drawer_close)
        drawer.addDrawerListener(toggle)

        listItems.layoutManager = LinearLayoutManager(this)
        listItems.adapter = NoteRecyclerAdapter(this)

        val view = layoutInflater.inflate(R.layout.drawer_header, navigationDrawer, true)
        animationDrawable = view.menu_header.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        populateNoteList()
        manageToast()

    }

    private fun manageToast() {
        val stringExtra = intent.getStringExtra("toastInfo")
        if (stringExtra != null) {
            Toast.makeText(this, "Note $stringExtra deleted!", Toast.LENGTH_LONG).show()
        }
    }

    private fun populateNoteList() = listItems.adapter?.notifyDataSetChanged()

    private fun setOnClickListener() {
        fab.setOnClickListener { view ->
            val activityIntent = Intent(this, NoteActivity::class.java)
            startActivity(activityIntent)
        }
    }
}
