package com.example.still

import java.util.*
import android.content.Intent
import android.os.Bundle
import android.view.AbsSavedState
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView

class songAdapter(private val songList: ArrayList<SongClass> ) : RecyclerView.Adapter<songAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {


        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.song, parent, false)
        return MyViewHolder(itemView)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {



        val song: SongClass = songList[position]
        holder.SongName.text = song.SongName
        holder.Genre.text = song.Genre

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, PlaySongActivity::class.java)

            intent.putExtra("SongName", song.SongName)
            intent.putExtra("SongUrl", song.Song)

            holder.itemView.context.startActivity((intent))
        }


    }

    override fun getItemCount(): Int {
        return songList.size
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val SongName: TextView = itemView.findViewById(R.id.songTitle)
        val Genre: TextView = itemView.findViewById(R.id.Genre)




    }



    }
