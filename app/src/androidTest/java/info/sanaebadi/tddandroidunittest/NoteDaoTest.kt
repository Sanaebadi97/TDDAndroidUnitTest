package info.sanaebadi.tddandroidunittest

import android.database.sqlite.SQLiteConstraintException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import info.sanaebadi.tddandroidunittest.model.Note
import info.sanaebadi.tddandroidunittest.util.LiveDataTestUtil
import info.sanaebadi.tddandroidunittest.util.TestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.matchers.Not


class NoteDaoTest : NoteDatabaseTest() {


    companion object {
        const val TITLE_TEST = "This is Title test"
        const val CONTENT_TEST = "This is Content test"
        const val TIMESTAMP_TEST = "04-2020"
    }


    @Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    // Insert , Read , Delete

    @Test
    @Throws(java.lang.Exception::class)
    internal fun insertReadDelete() {

        val note = Note(TestUtil.TEST_NOTE_1)

        //insert
        getNoteDao().insertNote(note)!!.blockingGet() //wait until inserted


        // read

        // read
        var liveDataTestUtil = LiveDataTestUtil<List<Note>>()
        var insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes())!!

        assertNotNull(insertedNotes)
        assertEquals(note.content, insertedNotes[0].content)
        assertEquals(note.title, insertedNotes[0].title)
        assertEquals(note.timestamp, insertedNotes[0].timestamp)

        note.id = insertedNotes[0].id
        assertEquals(note, insertedNotes[0])


        //delete
        getNoteDao().deleteNote(note).blockingGet()

        //confirm the database is empty
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes())!!
        assertEquals(0, insertedNotes.size)
    }


    //Insert , Read , Update , Delete

    @Test
    @Throws(java.lang.Exception::class)
    internal fun insertReadUpdateReadDelete() {

        val note = Note(TestUtil.TEST_NOTE_1)

        //insert
        getNoteDao().insertNote(note)!!.blockingGet() //wait until inserted


        // read

        // read
        var liveDataTestUtil = LiveDataTestUtil<List<Note>>()
        var insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes())!!

        assertNotNull(insertedNotes)
        assertEquals(note.content, insertedNotes[0].content)
        assertEquals(note.title, insertedNotes[0].title)
        assertEquals(note.timestamp, insertedNotes[0].timestamp)

        note.id = insertedNotes[0].id
        assertEquals(note, insertedNotes[0])


        //delete
        getNoteDao().deleteNote(note).blockingGet()

        //update
        note.title = TITLE_TEST
        note.content = CONTENT_TEST
        note.timestamp = TIMESTAMP_TEST
        getNoteDao().updateNote(note).blockingGet()


        //read
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes())

        assertEquals(TITLE_TEST, insertedNotes[0].title)
        assertEquals(CONTENT_TEST, insertedNotes[0].content)
        assertEquals(TIMESTAMP_TEST, insertedNotes[0].timestamp)


        note.id = insertedNotes[0].id
        assertEquals(note, insertedNotes[0])


        //confirm the database is empty
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().getNotes())!!
        assertEquals(0, insertedNotes.size)
    }


    //Insert Note with null title throw exception

    @Test(expected = SQLiteConstraintException::class)
    @Throws(java.lang.Exception::class)
    internal fun insert_nullTitle_throwSQLiteConstraintException() {

        val note = Note(TestUtil.TEST_NOTE_2)
        note.title = null

        //insert
        getNoteDao().insertNote(note)!!.blockingGet()

    }


    //Insert , Update  Note with null title throw exception

    @Test(expected = SQLiteConstraintException::class)
    @Throws(java.lang.Exception::class)
    internal fun update_nullTitle_throwSQLiteConstraintException() {

        var note = Note(TestUtil.TEST_NOTE_1)
        getNoteDao().insertNote(note)!!.blockingGet()

        //read
        var liveDataTestUtil: LiveDataTestUtil<List<Note>> = LiveDataTestUtil()
        var insertNotes: List<Note> = liveDataTestUtil.getValue(getNoteDao().getNotes())!!
        assertNotNull(insertNotes)


        //update
        //update
        note = Note(insertNotes[0])
        note.title = null
        getNoteDao().updateNote(note).blockingGet()

    }
}