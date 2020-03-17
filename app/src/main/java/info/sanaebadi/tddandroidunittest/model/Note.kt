package info.sanaebadi.tddandroidunittest.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor


@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @Ignore
    var id: Int? = null,

    @NonNull
    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "content")
    val content: String? = null,

    @ColumnInfo(name = "timestamp")
    val timestamp: String? = null


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }


    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (javaClass != other.javaClass) {
            return false
        }

        var note: Note = other as Note

        return note.id == id && note.title == title && note.content == content
    }


}


