package com.example.still

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(
    private val noteList: MutableList<Note>,
    private val onEditClicked: (Note) -> Unit,
    private val onDeleteClicked: (Note) -> Unit,
    private val onViewClicked: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view, onEditClicked, onDeleteClicked, onViewClicked)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    class NoteViewHolder(
        itemView: View,
        private val onEditClicked: (Note) -> Unit,
        private val onDeleteClicked: (Note) -> Unit,
        private val onViewClicked: (Note) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView: TextView = itemView.findViewById(R.id.noteTitleText)
        private val dateTextView: TextView = itemView.findViewById(R.id.noteDateText)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        private val viewButton: Button = itemView.findViewById(R.id.viewButton)

        fun bind(note: Note) {
            titleTextView.text = note.title
            dateTextView.text = note.date

            // Make the whole item clickable for editing
            itemView.setOnClickListener {
                onEditClicked(note)
            }

            deleteButton.setOnClickListener {
                onDeleteClicked(note)
            }

            viewButton.setOnClickListener {
                onViewClicked(note)
            }
        }
    }
}
