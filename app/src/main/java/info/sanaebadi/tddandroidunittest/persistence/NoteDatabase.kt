package info.sanaebadi.tddandroidunittest.persistence

import androidx.room.Database
import info.sanaebadi.tddandroidunittest.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase {

    companion object {
        const val DATABASE_NAME = "notes_db"
    }

    abstract fun gtNoteDao(): NoteDao
}