package info.sanaebadi.tddandroidunittest.ui.noteslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import info.sanaebadi.tddandroidunittest.models.Note
import info.sanaebadi.tddandroidunittest.repository.NoteRepository
import info.sanaebadi.tddandroidunittest.ui.Resource
import javax.inject.Inject

class NotesListViewModel @Inject constructor(// inject
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val notes =
        MediatorLiveData<List<Note>>()

    @Throws(Exception::class)
    fun deleteNote(note: Note?): LiveData<Resource<Int>> {
        return noteRepository.deleteNote(note)
    }

    fun observeNotes(): LiveData<List<Note>> {
        return notes
    }

    fun getNotes() {
        val source =
            noteRepository.notes
        notes.addSource(
            source
        ) { notesList ->
            if (notesList != null) {
                notes.value = notesList
            }
            notes.removeSource(
                source
            )
        }
    }

    companion object {
        private const val TAG = "NotesListViewModel"
    }

}