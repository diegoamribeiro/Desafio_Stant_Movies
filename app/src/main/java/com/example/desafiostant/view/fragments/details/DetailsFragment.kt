package com.example.desafiostant.view.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.desafiostant.databinding.FragmentDetailsBinding
import com.example.desafiostant.utils.Constants.Companion.POSTER_BASE_URL

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs<DetailsFragmentArgs>()
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.ivCurrentDetail.load(POSTER_BASE_URL + args.curentItem.backdrop_path)
        binding.tvCurrentTitle.text = args.curentItem.title
        binding.tvOverview.text = args.curentItem.overview
        binding.tvCurrentGenre.text = args.curentItem.genre_ids.toString()
        binding.tvCurrentRelease.text = args.curentItem.release_date
        binding.tvCurrentLanguage.text = args.curentItem.original_language

        return binding.root
    }


}