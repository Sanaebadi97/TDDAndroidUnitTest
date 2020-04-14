package info.sanaebadi.tddandroidunittest.di

import android.app.Application
import androidx.room.Room
import dagger.Provides
import info.sanaebadi.tddandroidunittest.persistence.NoteDao
import info.sanaebadi.tddandroidunittest.persistence.NoteDatabase
import info.sanaebadi.tddandroidunittest.repository.NoteRepository
import javax.inject.Singleton

internal object AppModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideNoteDatabase(application: Application?): NoteDatabase {
        return Room.databaseBuilder(
            application!!,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao?): NoteRepository {
        return NoteRepository(noteDao!!)
    }
}