package info.sanaebadi.tddandroidunittest.ui.noteslist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.sanaebadi.tddandroidunittest.R
import info.sanaebadi.tddandroidunittest.models.Note
import info.sanaebadi.tddandroidunittest.util.DateUtil
import java.util.*

class NotesRecyclerAdapter(private val onNoteListener: OnNoteListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var notes: MutableList<Note> =
        ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_note_list_item, parent, false)
        return ViewHolder(
            view,
            onNoteListener
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        try {
            var month = notes[position].timestamp!!.substring(0, 2)
            month = DateUtil.getMonthFromNumber(month)
            val year = notes[position].timestamp!!.substring(3)
            val timestamp = "$month $year"
            (holder as ViewHolder).timestamp.text = timestamp
            holder.title.text = notes[position].title
        } catch (e: NullPointerException) {
            Log.e(
                TAG,
                "onBindViewHolder: Null Pointer: " + e.message
            )
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun getNote(position: Int): Note? {
        return if (notes.size > 0) {
            notes[position]
        } else null
    }

    fun removeNote(note: Note) {
        notes.remove(note)
        notifyDataSetChanged()
    }

    fun setNotes(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        itemView: View,
        onNoteListener: OnNoteListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var timestamp: TextView
        var title: TextView
        var mOnNoteListener: OnNoteListener
        override fun onClick(view: View) {
            Log.d(
                TAG,
                "onClick: $adapterPosition"
            )
            mOnNoteListener.onNoteClick(getNote(adapterPosition))
        }

        init {
            timestamp = itemView.findViewById(R.id.note_timestamp)
            title = itemView.findViewById(R.id.note_title)
            mOnNoteListener = onNoteListener
            itemView.setOnClickListener(this)
        }
    }

    interface OnNoteListener {
        fun onNoteClick(note: Note?)
    }

    companion object {
        private const val TAG = "NotesRecyclerAdapter"
    }

}