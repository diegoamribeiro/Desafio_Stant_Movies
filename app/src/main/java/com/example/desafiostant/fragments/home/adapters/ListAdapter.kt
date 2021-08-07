package com.example.desafiostant.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiostant.R
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.databinding.ItemMovieBinding
import kotlinx.android.synthetic.main.item_movie.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var dataList = emptyList<Movie>()
    class ListViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.tvTitle.text = dataList[position].title
        holder.binding.tvYear.text = dataList[position].release_date

    }

    override fun getItemCount(): Int{
        return dataList.size
    }


}
