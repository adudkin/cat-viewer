package com.example.catviewer.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catviewer.R
import com.example.catviewer.databinding.ListItemBinding
import com.example.catviewer.room.CatData

class CatEntityListAdapter : RecyclerView.Adapter<CatEntityListAdapter.ViewHolder>() {

    var items: List<CatData> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.root).load(items[position].url).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ListItemBinding.bind(view)

    }
}