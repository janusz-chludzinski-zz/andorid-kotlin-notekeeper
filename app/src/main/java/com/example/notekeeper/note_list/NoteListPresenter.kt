package com.example.notekeeper.note_list

import com.example.notekeeper.DataManager

class NoteListPresenter(val activity: NoteListActivity) {

    fun getAllNotes() = DataManager.notes

}