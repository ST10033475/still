package com.example.still

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NoteViewActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var noteList: MutableList<Note>
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_view)

        // Initialize Firebase reference
        database = FirebaseDatabase.getInstance().reference.child("notes")

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userUUID = currentUser?.uid  // Get the current user's ID

        // Initialize RecyclerView with GridLayoutManager for 3 items per row
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        noteList = mutableListOf()
        noteAdapter = NoteAdapter(noteList, { note -> editNote(note) }, { note -> deleteNote(note) }) { note -> viewNoteDetail(note)
        }
        recyclerView.adapter = noteAdapter

        findViewById<Button>(R.id.backButton2).setOnClickListener {
            finish()  // Finish the activity and go back to the previous one
        }

        // Fetch notes from Firebase
        fetchNotesFromFirebase()
    }

    private fun viewNoteDetail(note: Note) {
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.description)
        intent.putExtra("noteDate", note.date)
        startActivity(intent)
    }

    // Function to fetch all notes from Firebase
    private fun fetchNotesFromFirebase() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userUUID = currentUser?.uid  // Get the current user's ID

        database.orderByChild("uuid").equalTo(userUUID)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    noteList.clear() // Clear the list to avoid duplication
                    for (noteSnapshot in snapshot.children) {
                        val note = noteSnapshot.getValue(Note::class.java)
                        note?.let {
                            it.id = noteSnapshot.key ?: "" // Set the ID
                            noteList.add(it)
                        }
                    }
                    noteAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@NoteViewActivity, "Failed to load notes", Toast.LENGTH_SHORT).show()
                }
            })
    }

    // Function to handle note editing
    private fun editNote(note: Note) {
        val intent = Intent(this, MainNoteActivity::class.java)
        intent.putExtra("noteId", note.id) // Pass the note's ID
        intent.putExtra("noteTitle", note.title) // Pass the note's title
        intent.putExtra("noteDescription", note.description) // Pass the note's description
        startActivity(intent)
    }

    // Function to handle note deletion
    private fun deleteNote(note: Note) {
        database.child(note.id).removeValue() // Use the correct note ID
            .addOnSuccessListener {
                noteList.remove(note) // Remove from the local list
                noteAdapter.notifyDataSetChanged() // Notify the adapter of the change
                Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to delete note", Toast.LENGTH_SHORT).show()
            }
    }
}
