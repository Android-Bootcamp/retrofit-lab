package com.pinterest.lab.ui

import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.pinterest.lab.R

class ProgramListAdapter internal constructor(private val mData: List<String>) :
    RecyclerView.Adapter<ProgramListAdapter.ViewHolder>() {

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tv: TextView = itemView.findViewById(R.id.tvProgramName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.program_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = mData[position]
        holder.tv.text = program
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}