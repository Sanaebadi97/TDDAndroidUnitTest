package info.sanaebadi.tddandroidunittest;

import android.database.sqlite.SQLiteConstraintException;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import info.sanaebadi.tddandroidunittest.models.Note;
import info.sanaebadi.tddandroidunittest.util.LiveDataTestUtil;
import info.sanaebadi.tddandroidunittest.util.TestUtil;

import static org.junit.Assert.*;

public class NoteDaoTest extends NoteDatabaseTest {

    public static final String TEST_TITLE = "This is a test title";
    public static final String TEST_CONTENT = "This is some test content";
    public static final String TEST_TIMESTAMP = "08-2018";

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    /*
        Insert, Read, Delete
     */
    @Test
    public void insertReadDelete() throws Exception{

        Note note = new Note(TestUtil.TEST_NOTE_1);

        // insert
        getNoteDao().insertNote(note).blockingGet(); // wait until inserted

        // read
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().notes);

        assertNotNull(insertedNotes);

        assertEquals(note.content, insertedNotes.get(0).content);
        assertEquals(note.timestamp, insertedNotes.get(0).timestamp);
        assertEquals(note.title, insertedNotes.get(0).title);

        note.id = insertedNotes.get(0).id;
        assertEquals(note, insertedNotes.get(0));

        // delete
        getNoteDao().deleteNote(note).blockingGet();

        // confirm the database is empty
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().notes);
        assertEquals(0, insertedNotes.size());
    }


    /*
        Insert, Read, Update, Read, Delete,
     */
    @Test
    public void insertReadUpdateReadDelete() throws Exception{

        Note note = new Note(TestUtil.TEST_NOTE_1);

        // insert
        getNoteDao().insertNote(note).blockingGet(); // wait until inserted

        // read
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().notes);

        assertNotNull(insertedNotes);

        assertEquals(note.content, insertedNotes.get(0).content);
        assertEquals(note.timestamp, insertedNotes.get(0).timestamp);
        assertEquals(note.title, insertedNotes.get(0).title);

        note.id = insertedNotes.get(0).id;
        assertEquals(note, insertedNotes.get(0));

        // update
        note.title = TEST_TITLE;
        note.content = TEST_CONTENT;
        note.timestamp = TEST_TIMESTAMP;
        getNoteDao().updateNote(note).blockingGet();

        // read
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().notes);

        assertEquals(TEST_TITLE, insertedNotes.get(0).title);
        assertEquals(TEST_CONTENT, insertedNotes.get(0).content);
        assertEquals(TEST_TIMESTAMP, insertedNotes.get(0).timestamp);

        note.id = insertedNotes.get(0).id;
        assertEquals(note, insertedNotes.get(0));

        // delete
        getNoteDao().deleteNote(note).blockingGet();

        // confirm the database is empty
        insertedNotes = liveDataTestUtil.getValue(getNoteDao().notes);
        assertEquals(0, insertedNotes.size());
    }



    /*
        Insert note with null title, throw exception
     */
    @Test(expected = SQLiteConstraintException.class)
    public void insert_nullTitle_throwSQLiteConstraintException() throws Exception{

        final Note note = new Note(TestUtil.TEST_NOTE_1);
        note.title = null;

        // insert
        getNoteDao().insertNote(note).blockingGet();
    }


    /*
        Insert, Update with null title, throw exception
     */

    @Test(expected = SQLiteConstraintException.class)
    public void updateNote_nullTitle_throwSQLiteConstraintException() throws Exception{

        Note note = new Note(TestUtil.TEST_NOTE_1);

        // insert
        getNoteDao().insertNote(note).blockingGet();

        // read
        LiveDataTestUtil<List<Note>> liveDataTestUtil = new LiveDataTestUtil<>();
        List<Note> insertedNotes = liveDataTestUtil.getValue(getNoteDao().notes);
        assertNotNull(insertedNotes);

        // update
        note = new Note(insertedNotes.get(0));
        note.title = null;
        getNoteDao().updateNote(note).blockingGet();

    }


}
















