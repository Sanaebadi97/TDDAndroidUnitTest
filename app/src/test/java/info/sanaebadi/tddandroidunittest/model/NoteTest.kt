package info.sanaebadi.tddandroidunittest.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.Exception

class NoteTest {


    companion object {

        const val TIMESTAMP_1 = "05-1998"
        const val TIMESTAMP_2 = "04-2020"
    }

    /*

    Compare two equal   Notes
     */
    @Test
    internal fun isNotesEqual_identicalProperties_returnTrue() {

        //Arrange

        val note1: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)

        val note2: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)


        //Act


        //Assert
        assertEquals(note1, note2)
        println("The notes are equals!")
    }

    /*

    Compare notes with 2 different  ids
     */

    @Test
    internal fun isNotesEqual_differentIds_returnFalse() {

        //Arrange

        val note1: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)

        val note2: Note =
            Note(id = 2, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)


        //Act


        //Assert
        assertNotEquals(note1, note2)
        println("The notes are not equals!")

    }


    /*
    Compare two notes with different timestamp

     */
    @Test
    internal fun isNotesEqual_differentTimestamps_returnTrue() {

        //Arrange

        val note1: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)

        val note2: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_2)


        //Act


        //Assert
        assertEquals(note1, note2)
        println("The notes are  equals!")

    }


    /*

    Compare two notes with different title
     */
    @Test
    internal fun isNotesEqual_differentTitle_returnFalse() {

        //Arrange

        val note1: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)

        val note2: Note =
            Note(id = 1, title = "Note #2", content = "This is note #1", timestamp = TIMESTAMP_2)


        //Act


        //Assert
        assertNotEquals(note1, note2)
        println("The notes are not equals! they have different titles")
    }
/*

    Compare two notes with different content
     */

    @Test
    internal fun isNotesEqual_differentContent_returnFalse() {

        //Arrange

        val note1: Note =
            Note(id = 1, title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)

        val note2: Note =
            Note(id = 1, title = "Note #1", content = "This is note #2", timestamp = TIMESTAMP_2)


        //Act


        //Assert
        assertNotEquals(note1, note2)
        println("The notes are not equals! they have different content")
    }


}