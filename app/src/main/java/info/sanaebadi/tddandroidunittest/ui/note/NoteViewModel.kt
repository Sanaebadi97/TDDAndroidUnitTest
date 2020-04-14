package info.sanaebadi.tddandroidunittest.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import info.sanaebadi.tddandroidunittest.models.Note
import info.sanaebadi.tddandroidunittest.repository.NoteRepository
import info.sanaebadi.tddandroidunittest.ui.Resource
import info.sanaebadi.tddandroidunittest.util.DateUtil
import org.reactivestreams.Subscription
import javax.inject.Inject

class NoteViewModel @Inject constructor(// inject
    private val noteRepository: NoteRepository
) : ViewModel() {
    enum class ViewState {
        VIEW, EDIT
    }

    // vars
    private val note =
        MutableLiveData<Note?>()
    private val viewState = MutableLiveData<ViewState>()
    private var isNewNote = false
    private var updateSubscription: Subscription? = null
    private var insertSubscription: Subscription? = null

    @Throws(Exception::class)
    fun insertNote(): LiveData<Resource<Int>> {
        return LiveDataReactiveStreams.fromPublisher(
            noteRepository.insertNote(note.value)
                .doOnSubscribe { subscription -> insertSubscription = subscription }
        )
    }

    @Throws(Exception::class)
    fun updateNote(): LiveData<Resource<Int>> {
        return LiveDataReactiveStreams.fromPublisher(
            noteRepository.updateNote(note.value)
                .doOnSubscribe { subscription -> updateSubscription = subscription }
        )
    }

    fun observeNote(): LiveData<Note?> {
        return note
    }

    fun observeViewState(): LiveData<ViewState> {
        return viewState
    }

    fun setViewState(viewState: ViewState) {
        this.viewState.value = viewState
    }

    fun setIsNewNote(isNewNote: Boolean) {
        this.isNewNote = isNewNote
    }

    @Throws(Exception::class)
    fun saveNote(): LiveData<Resource<Int?>> {
        if (!shouldAllowSave()) {
            throw Exception(NO_CONTENT_ERROR)
        }
        cancelPendingTransactions()
        return object : NoteInsertUpdateHelper<Int?>() {
            override fun setNoteId(noteId: Int) {
                isNewNote = false
                val currentNote = note.value
                currentNote!!.id = noteId
                note.setValue(currentNote)
            }

            @get:Throws(Exception::class)
            override val action: LiveData<Resource<Int>>
                get() = if (isNewNote) {
                    insertNote()
                } else {
                    updateNote()
                }

            override fun defineAction(): String {
                return if (isNewNote) {
                    ACTION_INSERT
                } else {
                    ACTION_UPDATE
                }
            }

            override fun onTransactionComplete() {
                updateSubscription = null
                insertSubscription = null
            }
        }.asLiveData
    }

    private fun cancelPendingTransactions() {
        if (insertSubscription != null) {
            cancelInsertTransaction()
        }
        if (updateSubscription != null) {
            cancelUpdateTransaction()
        }
    }

    private fun cancelUpdateTransaction() {
        updateSubscription!!.cancel()
        updateSubscription = null
    }

    private fun cancelInsertTransaction() {
        insertSubscription!!.cancel()
        insertSubscription = null
    }

    @Throws(Exception::class)
    private fun shouldAllowSave(): Boolean {
        return try {
            removeWhiteSpace(note.value!!.content).length > 0
        } catch (e: NullPointerException) {
            throw Exception(NO_CONTENT_ERROR)
        }
    }

    @Throws(Exception::class)
    fun updateNote(title: String?, content: String?) {
        if (title == null || title == "") {
            throw NullPointerException("Title can't be null")
        }
        val temp = removeWhiteSpace(content)
        if (temp.length > 0) {
            val updatedNote =
                Note(note.value!!)
            updatedNote.title = title
            updatedNote.content = content
            updatedNote.timestamp = DateUtil.currentTimeStamp
            note.value = updatedNote
        }
    }

    private fun removeWhiteSpace(string: String?): String {
        var string = string
        string = string!!.replace("\n", "")
        string = string.replace(" ", "")
        return string
    }

    @Throws(Exception::class)
    fun setNote(note: Note) {
        if (note.title == null || note.title == "") {
            throw Exception(NoteRepository.NOTE_TITLE_NULL)
        }
        this.note.value = note
    }

    fun shouldNavigateBack(): Boolean {
        return if (viewState.value == ViewState.VIEW) {
            true
        } else {
            false
        }
    }

    companion object {
        private const val TAG = "NoteViewModel"
        const val NO_CONTENT_ERROR = "Can't save note with no content"
    }

}