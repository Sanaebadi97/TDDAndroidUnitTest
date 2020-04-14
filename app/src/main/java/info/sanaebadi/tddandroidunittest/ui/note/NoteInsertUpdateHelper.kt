package info.sanaebadi.tddandroidunittest.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import info.sanaebadi.tddandroidunittest.ui.Resource

abstract class NoteInsertUpdateHelper<T> {
    private val result =
        MediatorLiveData<Resource<T?>>()

    private fun init() {
        result.value = Resource.loading<Any?>(null) as Resource<T?>
        try {
            val source = action
            result.addSource(
                source,
                Observer<Resource<T?>> { tResource ->
                    result.removeSource(source)
                    result.setValue(tResource)
                    setNewNoteIdIfIsNewNote(tResource)
                    onTransactionComplete()
                })
        } catch (e: Exception) {
            e.printStackTrace()
            result.setValue(
                Resource.error(
                    null,
                    GENERIC_ERROR
                )
            )
        }
    }

    private fun setNewNoteIdIfIsNewNote(resource: Resource<T?>) {
        if (resource.data != null) {
            if (resource.data.javaClass == Int::class.java) {
                val i = resource.data as Int
                if (defineAction() == ACTION_INSERT) {
                    if (i >= 0) {
                        setNoteId(i)
                    }
                }
            }
        }
    }

    abstract fun setNoteId(noteId: Int)

    @get:Throws(Exception::class)
    abstract val action: LiveData<Resource<T>>
    abstract fun defineAction(): String
    abstract fun onTransactionComplete()
    val asLiveData: LiveData<Resource<T?>>
        get() = result

    companion object {
        const val ACTION_INSERT = "ACTION_INSERT"
        const val ACTION_UPDATE = "ACTION_UPDATE"
        const val GENERIC_ERROR = "Something went wrong"
    }

    init {
        init()
    }
}