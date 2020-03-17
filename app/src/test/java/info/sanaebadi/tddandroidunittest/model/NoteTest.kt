package info.sanaebadi.tddandroidunittest.model

import org.junit.jupiter.api.Assertions
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
            Note(title = "Note #1", content = "This is note #1", timestamp = TIMESTAMP_1)
        note1.id = 1

        val note2: Note =
            Note(title = "Note #2", content = "This is note #2", timestamp = TIMESTAMP_2)
        note2.id = 2


        //Act


        //Assert
        Assertions.assertEquals(note1, note2)
        println("The notes are equals!")
    }

    /*

    Compare notes with 2 different  ids
     */


    /*

    Compare two notes with different timestamp

     */


    /*

    Compare two notes with different title
     */


    /*

    Compare two notes with different content
     */


}