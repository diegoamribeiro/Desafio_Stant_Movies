package com.example.desafiostant.view.fragments.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafiostant.R
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.databinding.ItemMovieBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ListViewHolder>() {
    var dataList = emptyList<Movie>()
    class ListViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = dataList[position].title
            tvYear.text = dataList[position].release_date
            tvGenre.text = dataList[position].genre_ids[0].toString()
            ivCover.load(dataList[position].poster_path){
                placeholder(R.drawable.ic_movies)
                    .fallback(R.drawable.ic_broken)
            }
        }


    }

    override fun getItemCount(): Int{
        return dataList.size
    }

    fun setData(movieList: List<Movie>){
        val movieDiffUtil = MovieDiffUtil(dataList, movieList)
        val movieListResult = DiffUtil.calculateDiff(movieDiffUtil)
        this.dataList = movieList
        movieListResult.dispatchUpdatesTo(this)
    }


}
