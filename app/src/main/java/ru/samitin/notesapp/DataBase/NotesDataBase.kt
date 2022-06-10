package ru.samitin.notesapp.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.samitin.notesapp.Dao.NotesDao
import ru.samitin.notesapp.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDataBase : RoomDatabase(){
    abstract fun myNotesDao(): NotesDao

    companion object{
        @Volatile
        var INSTANCE : NotesDataBase?=null
        fun getDataBaseInstance(context: Context): NotesDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val rooDatabaseInstance = Room.databaseBuilder(context,NotesDataBase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE = rooDatabaseInstance
                return rooDatabaseInstance
            }
        }
    }
}