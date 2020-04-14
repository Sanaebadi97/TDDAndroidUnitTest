package info.sanaebadi.tddandroidunittest.di;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import info.sanaebadi.tddandroidunittest.ui.note.NoteActivity;
import info.sanaebadi.tddandroidunittest.ui.noteslist.NotesListActivity;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract NotesListActivity contributeNotesListActivity();

    @ContributesAndroidInjector
    abstract NoteActivity contributeNotesActivity();
}
