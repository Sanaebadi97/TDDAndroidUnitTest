package info.sanaebadi.tddandroidunittest

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import info.sanaebadi.tddandroidunittest.persistence.NoteDao
import info.sanaebadi.tddandroidunittest.persistence.NoteDatabase
import org.junit.After
import org.junit.Before

abstract class NoteDatabaseTest {

    var noteDatabase: NoteDatabase? = null

    fun getNoteDao(): NoteDao {
        return noteDatabase!!.gtNoteDao()
    }


    @Before
    fun init() {
        noteDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).build()
    }

    @After
    fun finish(){
        noteDatabase!!.close()
    }
}