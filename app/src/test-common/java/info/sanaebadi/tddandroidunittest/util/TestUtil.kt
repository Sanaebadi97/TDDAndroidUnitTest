package info.sanaebadi.tddandroidunittest.util

import info.sanaebadi.tddandroidunittest.model.Note
import java.util.*
import kotlin.collections.ArrayList

class TestUtil {

    companion object {
        val TIMESTAMP_1 = "05-2019"
        val TEST_NOTE_1: Note =
            Note("Take out the trash", "It's garbage day tomorrow.", TIMESTAMP_1)

        val TIMESTAMP_2 = "06-2019"
        val TEST_NOTE_2: Note = Note("Anniversary gift", "Buy an anniversary gift.", TIMESTAMP_2)

        val TEST_NOTES_LIST: List<Note> = Collections.unmodifiableList(
            object : ArrayList<Note?>() {
                init {
                    add(Note(1, "Take out the trash", "It's garbage day tomorrow.", TIMESTAMP_1))
                    add(Note(2, "Anniversary gift", "Buy an anniversary gift.", TIMESTAMP_2))
                }
            }
        ) as List<Note>
    }
}