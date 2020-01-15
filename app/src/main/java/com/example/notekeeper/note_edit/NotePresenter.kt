package com.example.notekeeper.note_edit

import com.example.notekeeper.DataManager
import com.example.notekeeper.NoteInfo
import com.example.notekeeper.POSITION_NOT_SET

class NotePresenter(private val activity: MainActivity) {

    private var notePosition = POSITION_NOT_SET

    fun updateValues(newNoteInfo: NoteInfo) {
        DataManager.notes[notePosition] = newNoteInfo
    }

    fun displayNote(notePosition: Int) {
        if(notePosition != POSITION_NOT_SET) {
            val note = DataManager.notes[notePosition]

            activity.setCourseInfo(resolveCoursePosition(note))
            activity.setNoteTitle(note.title)
            activity.setNoteText(note.note)
        }
    }

    fun displayNextNote() {
        resolveNotePosition()
        displayNote(notePosition)
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


}

