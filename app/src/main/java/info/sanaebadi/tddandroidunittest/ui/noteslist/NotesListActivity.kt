package info.sanaebadi.tddandroidunittest.ui.noteslist

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import info.sanaebadi.tddandroidunittest.R
import info.sanaebadi.tddandroidunittest.models.Note
import info.sanaebadi.tddandroidunittest.ui.Resource
import info.sanaebadi.tddandroidunittest.ui.note.NoteActivity
import info.sanaebadi.tddandroidunittest.ui.noteslist.NotesListActivity
import info.sanaebadi.tddandroidunittest.ui.noteslist.NotesRecyclerAdapter.OnNoteListener
import info.sanaebadi.tddandroidunittest.util.VerticalSpacingItemDecorator
import info.sanaebadi.tddandroidunittest.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class NotesListActivity : DaggerAppCompatActivity(), OnNoteListener,
    View.OnClickListener {
    // ui components
    private var recyclerView: RecyclerView? = null
    private var fab: FloatingActionButton? = null
    private var parent: CoordinatorLayout? = null

    // vars
    private var viewModel: NotesListViewModel? = null
    private var adapter: NotesRecyclerAdapter? = null

    @JvmField
    @Inject
    var providerFactory: ViewModelProviderFactory? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
        recyclerView = findViewById(R.id.recyclerview)
        fab = findViewById(R.id.fab)
        parent = findViewById(R.id.parent)
        fab.setOnClickListener(this)
        viewModel = ViewModelProviders.of(this, providerFactory).get(
            NotesListViewModel::class.java
        )
        initRecyclerView()
    }

    private fun subscribeObservers() {
        Log.d(TAG, "subscribeObservers: called.")
        viewModel!!.observeNotes().observe(
            this,
            Observer { notes ->
                if (notes != null) {
                    adapter!!.setNotes(notes)
                }
            })
        viewModel!!.getNotes()
    }

    override fun onStart() {
        super.onStart()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.addItemDecoration(VerticalSpacingItemDecorator(10))
        adapter = NotesRecyclerAdapter(this)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
        recyclerView!!.adapter = adapter
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(getString(R.string.intent_note), note)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showSnackBar(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            Snackbar.make(parent!!, message!!, Snackbar.LENGTH_SHORT).show()
        }
    }

    var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val note =
                    adapter!!.getNote(viewHolder.adapterPosition)
                adapter!!.removeNote(note)
                try {
                    val deleteAction =
                        viewModel!!.deleteNote(note)
                    deleteAction.observe(
                        this@NotesListActivity,
                        object :
                            Observer<Resource<Int?>?> {
                            override fun onChanged(integerResource: Resource<Int?>?) {
                                if (integerResource != null) {
                                    showSnackBar(integerResource.message)
                                }
                                deleteAction.removeObserver(this)
                            }
                        })
                } catch (e: Exception) {
                    e.printStackTrace()
                    showSnackBar(e.message)
                }
            }
        }

    companion object {
        private const val TAG = "NotesListActivity"
    }
}