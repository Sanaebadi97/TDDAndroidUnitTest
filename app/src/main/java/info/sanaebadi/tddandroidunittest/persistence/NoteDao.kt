package info.sanaebadi.tddandroidunittest.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import info.sanaebadi.tddandroidunittest.models.Note
import io.reactivex.Single

@Dao
interface NoteDao {
    @Insert
    @Throws(Exception::class)
    fun insertNote(note: Note?): Single<Long?>?

    @get:Query("SELECT * FROM notes")
    val notes: LiveData<List<Note?>?>?

    @Delete
    @Throws(Exception::class)
    fun deleteNote(note: Note?): Single<Int?>?

    @Update
    @Throws(Exception::class)
    fun updateNote(note: Note?): Single<Int?>?
}