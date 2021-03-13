package com.veryable.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.models.ActivityModel

internal class ActivityAdapter (private var activityList: List<ActivityModel>) :
    RecyclerView.Adapter<ActivityAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.title)
        var year: TextView = view.findViewById(R.id.year)
        var genre: TextView = view.findViewById(R.id.genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = activityList[position]
        holder.title.text = movie.getTitle()
        holder.genre.text = movie.getGenre()
        holder.year.text = movie.getYear()
    }

    override fun getItemCount(): Int {
        return activityList.size
    }
}
