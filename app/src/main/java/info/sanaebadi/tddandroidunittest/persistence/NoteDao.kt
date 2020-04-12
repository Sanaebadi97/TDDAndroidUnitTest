package info.sanaebadi.tddandroidunittest.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import info.sanaebadi.tddandroidunittest.model.Note
import io.reactivex.Single

@Dao
interface NoteDao {

    @Insert
    @Throws(Exception::class)
    open fun insertNote(note: Note?): Single<Long?>?

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>


    @Throws(java.lang.Exception::class)
    @Delete
    fun deleteNote(note: Note): Single<Int>

    @Throws(java.lang.Exception::class)
    @Update
    fun updateNote(note: Note): Single<Int>
}