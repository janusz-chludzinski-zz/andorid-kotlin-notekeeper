package com.example.notekeeper.note_edit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeper.*
import com.example.notekeeper.note_list.NoteListActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class NoteActivity : AppCompatActivity() {

    private val presenter = NotePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        configureOnFocusElevation()
        setupBetweenNoteNavigation()

        presenter.provideCoursesList()
        presenter.displayNote(getNotePosition())
        presenter.setupNotePager()
    }

    override fun onPause() {
        super.onPause()
        presenter.updateValues(getFieldData())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                presenter.deleteCurrentNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun propagateData(courses: List<CourseInfo>) {
        val adapterCourses = buildCoursesAdapter(courses)

        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = adapterCourses
    }

    fun setNoteText(note: String?) {
        textNoteText.setText(note)
    }

    fun setNoteTitle(title: String?) {
        textNoteTitle.setText(title)
    }

    fun setCourseInfo(coursePosition: Int) {
        spinnerCourses.setSelection(coursePosition)
    }

    fun setupNotePager(value: String) {
        notePager.text = value
    }

    fun goToNotesListView(text: String) {
        val intent = Intent(this, NoteListActivity::class.java)
        intent.putExtra("toastInfo", text)
        startActivity(intent)

    }

    private fun setupBetweenNoteNavigation() {
        actionNextBtn.setOnClickListener{_ ->  presenter.displayNextNote()}
        actionPrevBtn.setOnClickListener{_ -> presenter.displayPreviousNote()}
    }

    private fun configureOnFocusElevation() {
        textNoteText.setOnFocusChangeListener { v, hasFocus ->
            toggleElevationOnFocus(hasFocus, v)
        }

        textNoteTitle.setOnFocusChangeListener { v, hasFocus ->
            toggleElevationOnFocus(hasFocus, v)
        }
    }

    private fun toggleElevationOnFocus(hasFocus: Boolean, v: View) {
        if (hasFocus) v.elevation = 16f else v.elevation = 0f
    }

    private fun buildCoursesAdapter(courses: List<CourseInfo>): ArrayAdapter<CourseInfo> {
        return ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        )
    }

    private fun getFieldData(): NoteInfo {
        return NoteInfo(
            course = spinnerCourses.selectedItem as CourseInfo?,
            title = textNoteTitle.text.toString(),
            note = textNoteText.text.toString())
    }

    private fun getNotePosition(): Int {
        return intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
    }
}
