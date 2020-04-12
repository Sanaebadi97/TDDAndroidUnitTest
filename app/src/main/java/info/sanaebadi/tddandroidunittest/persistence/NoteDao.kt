package info.sanaebadi.tddandroidunittest.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import info.sanaebadi.tddandroidunittest.model.Note
import io.reactivex.Single

@Dao
interface NoteDao {

    @Insert
    @Throws(Exception::class)
    open fun insertNote(note: Note?): Single<Long?>?

    @Query("SELECT * FROM notes")
    fun getNotes() :LiveData<List<Note>>
}