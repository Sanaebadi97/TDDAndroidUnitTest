package info.sanaebadi.tddandroidunittest.util

import info.sanaebadi.tddandroidunittest.models.Note
import java.util.*

object TestUtil {
    const val TIMESTAMP_1 = "05-2019"
    @JvmField
    val TEST_NOTE_1 =
        Note(
            "Take out the trash",
            "It's garbage day tomorrow.",
            TIMESTAMP_1
        )
    const val TIMESTAMP_2 = "06-2019"
    val TEST_NOTE_2 =
        Note(
            "Anniversary gift",
            "Buy an anniversary gift.",
            TIMESTAMP_2
        )
    @JvmField
    val TEST_NOTES_LIST: List<Note> =
        Collections.unmodifiableList(
            object : ArrayList<Note?>() {
                init {
                    add(
                        Note(
                            1,
                            "Take out the trash",
                            "It's garbage day tomorrow.",
                            TIMESTAMP_1
                        )
                    )
                    add(
                        Note(
                            2,
                            "Anniversary gift",
                            "Buy an anniversary gift.",
                            TIMESTAMP_2
                        )
                    )
                }
            }
        ) as List<Note>
}