package info.sanaebadi.tddandroidunittest.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import info.sanaebadi.tddandroidunittest.persistence.NoteDao
import info.sanaebadi.tddandroidunittest.persistence.NoteDatabase
import info.sanaebadi.tddandroidunittest.repository.NoteRepository
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton


    @Provides fun provideNoteDatabase(application: Application): NoteDatabase? {
        return Room.databaseBuilder(
            application,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.gtNoteDao()
    }


    @Module
    companion object {
        @Singleton
        @JvmStatic
        @Provides

        fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
            return NoteRepository(noteDao)
        }
    }

}