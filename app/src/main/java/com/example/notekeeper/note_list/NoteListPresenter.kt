package com.example.notekeeper.note_list

import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeper.DataManager

class NoteListPresenter(val activity: NoteListActivity) {

    fun populateNoteList() {
        activity.populateNoteList(DataManager.notes)
    }



}