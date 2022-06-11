package ru.samitin.notesapp.Repository

import androidx.lifecycle.LiveData
import ru.samitin.notesapp.Dao.NotesDao
import ru.samitin.notesapp.Model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>>{
        return dao.getNotes()
    }
    fun getHighAllNotes(): LiveData<List<Notes>>{
        return dao.getHighNotes()
    }
    fun getMediumAllNotes(): LiveData<List<Notes>>{
        return dao.getMediumNotes()
    }
    fun getLowAllNotes(): LiveData<List<Notes>>{
        return dao.getLowNotes()
    }
    fun insertNotes(notes: Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}