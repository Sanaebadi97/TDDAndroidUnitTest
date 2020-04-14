package info.sanaebadi.tddandroidunittest.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import info.sanaebadi.tddandroidunittest.models.Note;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes_db";

    public abstract NoteDao getNoteDao();
}
