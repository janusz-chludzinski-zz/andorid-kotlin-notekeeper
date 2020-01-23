package com.example.notekeeper.note_list

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notekeeper.R
import com.example.notekeeper.R.string.drawer_close
import com.example.notekeeper.R.string.drawer_open
import com.example.notekeeper.note_edit.NoteActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*
import kotlinx.android.synthetic.main.drawer_header.*


class NoteListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val presenter = NoteListPresenter(this)

    private val toggle: ActionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(
            this,
            drawer,
            drawer_open,
            drawer_close
        )
    }

    private val notesLayoutManager by lazy { LinearLayoutManager(this) }

    private val courseLayoutManager by lazy { GridLayoutManager(this, 2) }

    private val noteRecyclerAdapter by lazy { NoteRecyclerAdapter(this) }

    private val courseRecyclerAdapter by lazy { CourseRecyclerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)
        setupNavigationDrawer()
        setOnClickListener()
        displayNotes()

        navigationDrawer.setNavigationItemSelectedListener(this)
        navigationDrawer.bringToFront()

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.drawer_notes -> {
                displayNotes()
            }

            R.id.drawer_courses -> {
                displayCourses()
            }

            R.id.drawer_share -> {

            }

            R.id.drawer_send -> {

            }
        }

        return true
    }

    private fun setupNavigationDrawer() {
        setupNavigationDrawerAnimation()
        setupNavigationDrawerToggle()
    }

    private fun setupNavigationDrawerToggle() {
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun displayNotes() {
        listItems.layoutManager = notesLayoutManager
        listItems.adapter = noteRecyclerAdapter

        navigationDrawer.menu.findItem(R.id.drawer_notes).isChecked = true
        closeDrawer()
    }

    private fun displayCourses() {
        listItems.layoutManager = courseLayoutManager
        listItems.adapter = courseRecyclerAdapter

        navigationDrawer.menu.findItem(R.id.drawer_courses).isChecked = true
        closeDrawer()
    }

    private fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun setupNavigationDrawerAnimation() {
        layoutInflater.inflate(R.layout.drawer_header, navigationDrawer, true)

        val animationDrawable = menu_header.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(2000)
            setExitFadeDuration(4000)
            start()
        }
    }

    private fun manageToast() {
        val stringExtra = intent.getStringExtra("toastInfo")
        if (stringExtra != null) {
            Snackbar.make(listItems, "Note $stringExtra deleted!", Snackbar.LENGTH_LONG).show()
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
