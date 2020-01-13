package com.example.notekeeper.note_edit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeper.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val presenter = NotePresenter(this)


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

        val notePosition = intent.getIntExtra(
            EXTRA_NOTE_POSITION,
            POSITION_NOT_SET
        )

        presenter.displayNote(notePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                presenter.displayNextNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        presenter.updateValues(getFieldData())
    }

    private fun getFieldData(): NoteInfo {
        return NoteInfo(
            course = spinnerCourses.selectedItem as CourseInfo?,
            title = textNoteTitle.text.toString(),
            note = textNoteText.text.toString())
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
}
