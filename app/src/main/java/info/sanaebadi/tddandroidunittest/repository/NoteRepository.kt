package info.sanaebadi.tddandroidunittest.repository

import androidx.annotation.NonNull
import info.sanaebadi.tddandroidunittest.persistence.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository {

    @NonNull
    //inject
    lateinit var noteDao:NoteDao

    @Inject
    constructor(@NonNull  noteDao: NoteDao){

    }

}