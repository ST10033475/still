package com.example.still

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.auth.User
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONObject

class FocusActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var songArrayList: ArrayList<SongClass>
    private lateinit var  songAdapter: songAdapter
    private lateinit var db: FirebaseFirestore

    private val TAG = "API Call"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus)
        // Initialize the RecyclerView and songArrayList here
        recyclerView = findViewById(R.id.songRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        songArrayList = arrayListOf() // Initialize the list here

        songAdapter = songAdapter(songArrayList) // Pass the empty list to the adapter
        recyclerView.adapter = songAdapter


        // API call setup
        val client = OkHttpClient()
        val url = "https://still-2004.uc.r.appspot.com/FocusSongs"

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e(TAG, "Request failed: ${e.message}")
                runOnUiThread {
                }
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val responseData = response.body?.string()
                Log.d(TAG, "Response data: $responseData")

                if (responseData != null) {
                    val jsonArray = org.json.JSONArray(responseData)

                    // Clear the list before adding new data
                    songArrayList.clear()

                    // Loop through the JSON array
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val songName = jsonObject.optString("SongName", "Unknown")
                        val genre = null
                        val songUrl = jsonObject.optString("Song", "")

                        // Create SongClass object and add to list
                        val song = SongClass( genre, songUrl, songName)
                        songArrayList.add(song)
                    }

                    // Update the RecyclerView on the main thread
                    runOnUiThread {
                        songAdapter.notifyDataSetChanged()
                    }

                } else {
                    runOnUiThread {
                    }
                }
            }
        })
    }



}
