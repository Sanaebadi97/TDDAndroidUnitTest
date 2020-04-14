package info.sanaebadi.tddandroidunittest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.sanaebadi.tddandroidunittest.ui.note.NoteActivity
import info.sanaebadi.tddandroidunittest.ui.noteslist.NotesListActivity

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeNotesListActivity(): NotesListActivity?

    @ContributesAndroidInjector
    abstract fun contributeNotesActivity(): NoteActivity?
}