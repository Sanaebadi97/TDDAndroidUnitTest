package info.sanaebadi.tddandroidunittest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.sanaebadi.tddandroidunittest.NoteListActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesNotesListActivity(): NoteListActivity

}