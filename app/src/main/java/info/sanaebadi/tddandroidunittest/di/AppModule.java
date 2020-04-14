package info.sanaebadi.tddandroidunittest.di;

import android.app.Application;

import androidx.room.Room;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.sanaebadi.tddandroidunittest.persistence.NoteDao;
import info.sanaebadi.tddandroidunittest.persistence.NoteDatabase;
import info.sanaebadi.tddandroidunittest.repository.NoteRepository;

import static info.sanaebadi.tddandroidunittest.persistence.NoteDatabase.DATABASE_NAME;


@Module
class AppModule {

    @Singleton
    @Provides
    static NoteDatabase provideNoteDatabase(Application application){
        return Room.databaseBuilder(
                application,
                NoteDatabase.class,
                DATABASE_NAME
        ).build();
    }

    @Singleton
    @Provides
    static NoteDao provideNoteDao(NoteDatabase noteDatabase){
        return noteDatabase.getNoteDao();
    }


    @Singleton
    @Provides
    static NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }
}















