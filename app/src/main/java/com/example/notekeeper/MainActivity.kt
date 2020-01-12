package com.example.notekeeper

import android.hardware.display.DisplayManager
import android.os.Bundle
import android.view.Display
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCourses.adapter = adapterCourses

        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)

        if(notePosition != POSITION_NOT_SET)
            displayNote()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                resolveNotePosition()
                displayNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        updateValues()
    }

    private fun resolveNotePosition() {
        if (notePosition >= DataManager.notes.lastIndex)
            notePosition = 0
        else
            ++notePosition
    }

    private fun updateValues() {
        val noteInfo = DataManager.notes[notePosition]

        noteInfo.course = spinnerCourses.selectedItem as CourseInfo?
        noteInfo.title = textNoteTitle.text.toString()
        noteInfo.note = textNoteText.text.toString()
    }

    private fun displayNote() {
        val note = setTextAndTitle()
        setCourseInfo(note)
    }

    private fun setTextAndTitle(): NoteInfo {
        val note = DataManager.notes[notePosition]
        textNoteTitle.setText(note.title)
        textNoteText.setText(note.note)
        return note
    }

    private fun setCourseInfo(note: NoteInfo) {
        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinnerCourses.setSelection(coursePosition)
    }
}
