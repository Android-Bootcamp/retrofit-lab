package com.pinterest.lab.ui

import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.pinterest.lab.R
import com.pinterest.lab.model.Result

class MovieListAdapter internal constructor(private val mData: MutableList<Result>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val vMovie = itemView.findViewById<ImageView>(R.id.ivMovie)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val TtvReleaseDate = itemView.findViewById<TextView>(R.id.tvReleaseDate)
        val tvVote = itemView.findViewById<TextView>(R.id.tvVote)
        val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = mData[position]
        
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun setData(data: List<Result>) {
        mData.clear()
        mData.addAll(data)
    }

    fun appendToData(data: List<Result>) {
        mData.addAll(data)
    }
}