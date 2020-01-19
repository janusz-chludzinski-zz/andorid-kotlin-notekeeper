package com.example.notekeeper.note_edit

import android.app.Activity
import android.content.Intent
import com.example.notekeeper.DataManager
import com.example.notekeeper.NOTE_POSITION
import com.example.notekeeper.NoteInfo
import com.example.notekeeper.POSITION_NOT_SET
import com.example.notekeeper.note_list.NoteListActivity

class NotePresenter(private val activity: NoteActivity) {

    private var notePosition = POSITION_NOT_SET

    fun updateValues(newNoteInfo: NoteInfo) {
        if (notePosition == POSITION_NOT_SET)
            createNewNote(newNoteInfo)
        else
            DataManager.notes[notePosition] = newNoteInfo
    }

    private fun createNewNote(newNoteInfo: NoteInfo) {
        DataManager.notes.add(newNoteInfo)
    }

    fun displayNote(notePosition: Int) {
        if (notePosition != POSITION_NOT_SET) {
            val note = DataManager.notes[notePosition]

            setActivityData(note)

            this.notePosition = notePosition
        }
    }

    fun displayNextNote() {
        resolveNotePosition()
        displayNote(notePosition)
    }

    fun provideCoursesList() {
        val courses = DataManager.courses.values.toList()
        activity.propagateData(courses)
    }

    private fun resolveNotePosition() {
        if (notePosition >= DataManager.notes.lastIndex)
            notePosition = 0
        else
            ++notePosition
    }

    private fun resolveCoursePosition(note: NoteInfo): Int {
        return DataManager.courses.values.indexOf(note.course)
    }

    private fun setActivityData(note: NoteInfo) {
        activity.setCourseInfo(resolveCoursePosition(note))
        activity.setNoteTitle(note.title)
        activity.setNoteText(note.note)
    }

    fun deleteCurrentNote() {
        DataManager.notes.removeAt(notePosition)
        val intent = Intent(activity, NoteListActivity::class.java)
        activity.startActivity(intent)
    }
}

