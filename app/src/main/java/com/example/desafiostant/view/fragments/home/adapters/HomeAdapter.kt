package com.example.desafiostant.view.fragments.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.desafiostant.R
import com.example.desafiostant.data.model.Movie
import com.example.desafiostant.databinding.ItemMovieBinding
import com.example.desafiostant.utils.Constants.Companion.POSTER_BASE_URL
import com.example.desafiostant.view.fragments.home.HomeFragmentDirections

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
            tvYear.text = dataList[position].release_date.subSequence(0,4)
            tvGenre.text = "Gênero"
            ivCover.load(POSTER_BASE_URL + dataList[position].poster_path){
                placeholder(R.drawable.ic_movies)
                    .fallback(R.drawable.ic_broken)

            }
            holder.itemView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(dataList[position])
                holder.itemView.findNavController().navigate(action)
                Log.d("***HomeAdapter", "${tvTitle.text}")
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
