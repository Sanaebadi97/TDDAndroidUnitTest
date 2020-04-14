package info.sanaebadi.tddandroidunittest.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import info.sanaebadi.tddandroidunittest.models.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao?

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}