package com.example.desafiostant.view.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.desafiostant.databinding.FragmentDetailsBinding
import com.example.desafiostant.utils.Constants.Companion.POSTER_BASE_URL
import com.example.desafiostant.utils.Utils.Companion.toGenre

class DetailsFragment : Fragment() {


    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        Glide.with(binding.ivCurrentDetail)
            .load(POSTER_BASE_URL + args.currentItem.backdrop_path)
            .into(binding.ivCurrentDetail)
        binding.tvCurrentTitle.text = args.currentItem.title
        binding.tvOverview.text = args.currentItem.overview
        binding.tvCurrentGenre.text = toGenre(args.currentItem.genre_ids?.get(0))
        binding.tvCurrentRelease.text = args.currentItem.release_date.subSequence(0, 4)
        binding.tvCurrentLanguage.text = args.currentItem.original_language

        return binding.root
    }


}