package info.sanaebadi.tddandroidunittest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import info.sanaebadi.tddandroidunittest.ui.note.NoteViewModel
import info.sanaebadi.tddandroidunittest.ui.noteslist.NotesListViewModel
import info.sanaebadi.tddandroidunittest.viewmodels.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory?): ViewModelProvider.Factory?

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun bindNoteViewModel(noteViewModel: NoteViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel::class)
    abstract fun bindNotesListViewModel(noteViewModel: NotesListViewModel?): ViewModel?
}