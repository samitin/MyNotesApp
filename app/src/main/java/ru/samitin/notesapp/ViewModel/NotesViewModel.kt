package ru.samitin.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.samitin.notesapp.DataBase.NotesDataBase
import ru.samitin.notesapp.Model.Notes
import ru.samitin.notesapp.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NotesRepository

    init {
        val dao = NotesDataBase.getDataBaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes){ repository.insertNotes(notes) }

    fun getNotes(): LiveData<List<Notes>> = repository.getAllNotes()

    fun deleteNotes(id : Int){repository.deleteNotes(id)}

    fun updateNotes(notes: Notes){repository.updateNotes(notes)}
}