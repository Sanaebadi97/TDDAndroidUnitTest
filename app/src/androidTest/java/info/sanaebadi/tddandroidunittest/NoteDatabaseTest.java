package info.sanaebadi.tddandroidunittest;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;


import org.junit.After;
import org.junit.Before;

import info.sanaebadi.tddandroidunittest.persistence.NoteDao;
import info.sanaebadi.tddandroidunittest.persistence.NoteDatabase;

public abstract class NoteDatabaseTest {

    // system under test
    private NoteDatabase noteDatabase;


    public NoteDao getNoteDao(){
        return noteDatabase.getNoteDao();
    }

    @Before
    public void init(){
        noteDatabase = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NoteDatabase.class
        ).build();
    }

    @After
    public void finish(){
        noteDatabase.close();
    }
}






