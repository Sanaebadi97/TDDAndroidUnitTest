package info.sanaebadi.tddandroidunittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import info.sanaebadi.tddandroidunittest.repository.NoteRepository
import javax.inject.Inject


class NoteListActivity : DaggerAppCompatActivity() {

    companion object {
        const val TAG = "NoteListActivity"
    }

    @Inject
    lateinit var noteRepository: NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        Log.i(TAG, "NoteRepo $noteRepository ")
    }
}
