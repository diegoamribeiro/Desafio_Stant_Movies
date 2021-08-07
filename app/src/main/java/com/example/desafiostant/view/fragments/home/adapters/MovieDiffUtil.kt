package com.example.desafiostant.view.fragments.home.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.desafiostant.data.model.Movie

class MovieDiffUtil(
    val oldList: List<Movie>,
    val newList: List<Movie>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}