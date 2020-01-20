package com.example.notekeeper.note_edit

import com.example.notekeeper.DataManager
import com.example.notekeeper.NoteInfo
import com.example.notekeeper.POSITION_NOT_SET

class NotePresenter(private val activity: NoteActivity) {

    private var notePosition = POSITION_NOT_SET
    private var isDeleteMode: Boolean = false

    fun updateValues(newNoteInfo: NoteInfo) {
        if (!isDeleteMode)
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

    fun deleteCurrentNote() {
        isDeleteMode = true
        DataManager.notes.removeAt(notePosition)
        activity.finish()
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
}

